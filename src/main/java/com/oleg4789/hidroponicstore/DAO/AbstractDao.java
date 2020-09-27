package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.BaseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T extends BaseEntity> implements BaseDao<T> {
    private final String SELECT_FROM = "SELECT * FROM ";
    private final String WHERE_USER_ID = " WHERE user_id= '";
    private final String CANT_GET_ENTITY_BY_ID = "Can't get user by id";
    private final String CANT_GET_ALL_ENTITY_FROM = "Can't get all entities from";
    private final String CANT_UPDATE = "Can't update";
    private final String CANT_REMOVE = "Can't remove";
    private final String CANT_ADD = "Can't add";
    private final String CANT_GET_ENTITIES_BY_PARAMETERS = "Can't get entities by parameters ";

    private final String DELETE_FROM  = "DELETE FROM ";
    private final String WHERE   = " WHERE ";
    private final String AND    = " AND ";


    private Connection connection;

    void setConnection(Connection connection) {
        this.connection = connection;
    }

    public abstract String getQueryForAdd();

    public abstract String getQueryForUpdate();

    public abstract String getTableName();

    public abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;

    public abstract void setVarForPreparedStatement(T entity, PreparedStatement preparedStatement);


    @Override
    public T getById(T entity, Integer id) throws  DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM + getTableName() + WHERE_USER_ID + entity.getId() + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            if (resultSet.next()) {
                return getEntityFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException(CANT_GET_ENTITY_BY_ID + id, e);
        }

        return null;
    }

    @Override
    public List getAll() throws DaoException {
        List<T> entityList = new ArrayList<>();
        String SQL = SELECT_FROM + getTableName();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                entityList.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(CANT_GET_ALL_ENTITY_FROM + getTableName(), e);

        }
        return entityList;
    }

    @Override
    public void update(T entity) throws DaoException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(getQueryForUpdate())) {

            setVarForPreparedStatement(entity, preparedStatement);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            throw new DaoException(CANT_UPDATE+ entity.toString(),e);
        }
    }

    @Override
    public void remove(T entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        String SQL = DELETE_FROM + getTableName() + WHERE_USER_ID + entity.getId();
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(CANT_REMOVE+ entity.toString(),e);

        }

    }

    @Override
    public void add(T entity) throws DaoException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(getQueryForAdd());

            setVarForPreparedStatement(entity, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(CANT_ADD+ entity.toString(),e);
        }
    }

    @Override
    public List getByParameters(Map parameters) throws DaoException {
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
            throw new DaoException(CANT_GET_ENTITIES_BY_PARAMETERS ,e);
        }

        //return null; метод  ведь должен возвращать list. почему он не ругается что я не возврашаю что нибуть нигде
        // кроме как в блоке try , ведь внутри try может не дойти до return entityList;
        // если бы ругался то наверное перенес возврашение листа с трая в конец метода или возврашал бы типа null
    }

    private String createQueryForGetByParameters(Map<String, String> parameters) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_FROM).append(getTableName()).append( WHERE );

        //    for (Map.Entry<String, String> entry : map.entrySet()) {
        //    System.out.println(entry.getKey()  + entry.getValue());

        for (String key : parameters.keySet()) {
            if (parameters.size() == 1) {
                query.append(key).append(" = '").append(parameters.get(key)).append("'");
                return query.toString();
            } else {
                query.append(key).append(" = '").append(parameters.get(key)).append("'").append( AND );
            }
        }
        return query.toString();
    }

}
