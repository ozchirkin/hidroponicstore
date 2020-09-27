package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.BaseEntity;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> {

    T getById(T entity, Integer id) throws  DaoException;

    List<T> getAll() throws DaoException;

    void update(T entity) throws DaoException;

    void remove(T entity) throws DaoException;

    void add(T entity) throws DaoException;

    List<T> getByParameters(Map< String, String> parameters ) throws DaoException;
}
