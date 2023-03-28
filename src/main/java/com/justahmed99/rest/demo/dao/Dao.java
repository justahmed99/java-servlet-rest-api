package com.justahmed99.rest.demo.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    List<T> findAll() throws SQLException;
    T findById(String id) throws SQLException;
    void save(T request) throws SQLException;
    void update(T request) throws SQLException;
    void delete(String id) throws SQLException;
}
