package com.justahmed99.rest.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justahmed99.rest.demo.config.DatabaseConfig;
import com.justahmed99.rest.demo.dao.ItemsDao;
import com.justahmed99.rest.demo.entity.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private ItemsDao dao;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource dataSource = DatabaseConfig.createDataSource();
        dao = new ItemsDao(dataSource);
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            if (id == null) {
                resp.setContentType("application/json");
                mapper.writeValue(resp.getWriter(), dao.findAll());
            } else {
                Items item = dao.findById(id);
                if (item == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    resp.setContentType("application/json");
                    mapper.writeValue(resp.getWriter(), item);
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Items item = mapper.readValue(req.getInputStream(), Items.class);
            dao.save(item);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setHeader("Location", req.getRequestURL().append("/").append(item.getId()).toString());
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getPathInfo().substring(1);
            Items existingItem = dao.findById(id);
            if (existingItem == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                Items updatedItem = mapper.readValue(req.getInputStream(), Items.class);
                updatedItem.setId(existingItem.getId());
                dao.update(updatedItem);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getPathInfo().substring(1);
            Items existingItem = dao.findById(id);
            if (existingItem == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                dao.delete(existingItem.getId());
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
