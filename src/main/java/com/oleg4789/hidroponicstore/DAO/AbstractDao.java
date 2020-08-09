package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.BaseEntity;
import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T extends BaseEntity> implements BaseDao<T> {
    private Connection connection;

    void setConnection(Connection connection) {
        this.connection = connection;
    }


    public abstract String getQueryForAdd();

    public abstract String getQueryForUpdate();

    public abstract String getTableName();

    public abstract T getEntityFromResultSet(ResultSet resultSet);

    public abstract void setVarForPreparedStatement(T entity, PreparedStatement preparedStatement);


    @Override
    public T getById(T entity, Integer id) throws SQLException, DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        String SQL = "SELECT * FROM " + getTableName() + " WHERE user_id= '" + entity.getId() + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            if (resultSet.next()) {
                return getEntityFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("Can't get user by id" + id, e);
        }
        return null;
    }

    @Override
    public List getAll() throws SQLException {
        List<T> entityList = new ArrayList<>();
        String SQL = "SELECT * FROM " + getTableName();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                entityList.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return entityList;
    }

    @Override
    public void update(T entity) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(getQueryForUpdate())) {

            setVarForPreparedStatement(entity, preparedStatement);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void remove(T entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM " + getTableName() + " WHERE user_id =?" + entity.getId();
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void add(T entity) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(getQueryForAdd());

            setVarForPreparedStatement(entity, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getByParameters(Map parameters) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        List<T> entityList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(createQueryForGetByParameters(parameters));
            if (resultSet.next()) {
                entityList.add(getEntityFromResultSet(resultSet));
            }
            return entityList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String createQueryForGetByParameters(Map<String, String> parameters) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT FROM ").append(getTableName()).append(" WHERE ");

        //    for (Map.Entry<String, String> entry : map.entrySet()) {
        //    System.out.println(entry.getKey()  + entry.getValue());

        for (String key : parameters.keySet()) {
            if (parameters.size() == 1) {
                query.append(key).append(" = '").append(parameters.get(key)).append("'");
                return query.toString();
            } else {
                query.append(key).append(" = '").append(parameters.get(key)).append("'").append(" AND ");
            }
        }
        return query.toString();
    }

}
