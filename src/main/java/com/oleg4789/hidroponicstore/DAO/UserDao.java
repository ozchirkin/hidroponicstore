package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.BaseEntity;
import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao<User> {

    private final String USER_ID = "user_id";
    private final String LOGIN = "login";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private final String PHONE_NUMBER = "phone_number";
    private final String BALANCE = "balance";
    private final String ROLE = "role";
    private final String TABLE_NAME = "Users";
    private final String QUERY_FOR_ADD = "INSERT INTO Users(user_id,first_name,second_name,login,password,email,phone_number,balance,role) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    private final String QUERY_FOR_UPDATE = "UPDATE USERS SET first_name=?,second_name=?,login=?,password=?," +
            "email=?,phone_number=?,balance=?,role=? WHERE user_id=?";

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getQueryForAdd() {
        return QUERY_FOR_ADD;
    }

    @Override
    public String getQueryForUpdate() {
        return QUERY_FOR_UPDATE;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            User user = new User();
            user.setId(resultSet.getInt(USER_ID));
            user.setFirstName(resultSet.getString(2));
            user.setSecondName(resultSet.getString(3));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setEmail(resultSet.getString(EMAIL));
            user.setTelephoneNumber(resultSet.getString(PHONE_NUMBER));
            user.setBalance(resultSet.getBigDecimal(BALANCE));
            user.setRole(Role.valueOf(resultSet.getString(ROLE)));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setVarForPreparedStatement(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getSecondName());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getTelephoneNumber());
            preparedStatement.setBigDecimal(8, user.getBalance());
            preparedStatement.setString(9, String.valueOf(Role.CUSTOMER));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
