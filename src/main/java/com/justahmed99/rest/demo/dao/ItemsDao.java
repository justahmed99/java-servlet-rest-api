package com.justahmed99.rest.demo.dao;

import com.justahmed99.rest.demo.entity.Items;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemsDao implements Dao<Items> {

    private static final String SELECT_ALL = "SELECT * FROM public.items WHERE is_active = true";
    private static final String SELECT_BY_ID = "SELECT * FROM public.items WHERE id = ? AND is_active = true";
    private static final String INSERT = "INSERT INTO public.items(id, name, item_number, price, is_active) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE public.items SET name = ?, item_number = ?, price = ? WHERE id = ?";
    private static final String DELETE = "UPDATE public.items SET is_active = false WHERE id = ?";

    private final DataSource dataSource;

    public ItemsDao(
            DataSource dataSource
    ) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Items> findAll() throws SQLException {
        List<Items> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Items item = new Items();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setItemNumber(resultSet.getInt("item_number"));
                item.setPrice(resultSet.getLong("price"));
                item.setActive(resultSet.getBoolean("is_active"));
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public Items findById(String id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Items item = new Items();
                    item.setId(resultSet.getString("id"));
                    item.setName(resultSet.getString("name"));
                    item.setItemNumber(resultSet.getInt("item_number"));
                    item.setPrice(resultSet.getLong("price"));
                    return item;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void save(Items request) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, request.getName());
            statement.setInt(3, request.getItemNumber());
            statement.setLong(4, request.getPrice());
            statement.setBoolean(5, true);
            statement.executeUpdate();
//            try (ResultSet resultSet = statement.getGeneratedKeys()) {
//                if (resultSet.next()) {
//                    item.setId(resultSet.getString(1));
//                }
//            }
        }
    }

    @Override
    public void update(Items request) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, request.getName());
            statement.setInt(2, request.getItemNumber());
            statement.setLong(3, request.getPrice());
            statement.setString(4, request.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }
}
