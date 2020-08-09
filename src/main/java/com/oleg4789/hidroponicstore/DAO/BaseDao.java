package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.BaseEntity;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> {

    T getById(T entity, Integer id) throws SQLException, DaoException;

    List<T> getAll() throws SQLException;

    void update(T entity) throws SQLException;

    void remove(T entity) throws SQLException;

    void add(T entity) throws SQLException;

    List<T> getByParameters(Map< String, String> parameters ) throws SQLException;
}
