package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void add(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        final String SQL = "INSERT INTO Users(user_id,first_name,second_name,login,password,email,phone_number,balance,role) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, user.getUserId());
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
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        final String SQL = "SELECT user_id,first_name,second_name,login,password,email,phone_number,balance,role FROM USERS";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return userList;
    }

    public User getUserById(int id) throws SQLException, DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        final String SQL = "SELECT * FROM mydb.users WHERE user_id=?";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("Can't get user by id" + id, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }

    public User getUserByLogin(String login) throws SQLException, DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        final String SQL = "SELECT * FROM mydb.users WHERE login = ? OR email = ?";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("not found users with such login or mail", e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null;
    }

    public void update(User user) throws SQLException {
        final String SQL = "UPDATE USERS SET first_name=?,second_name=?,login=?,password=?,email=?,phone_number=?,balance=?,role=? WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelephoneNumber());
            preparedStatement.setBigDecimal(7, user.getBalance());
            preparedStatement.setString(8, String.valueOf(Role.CUSTOMER));
            preparedStatement.setInt(9, user.getUserId());

            preparedStatement.executeUpdate();
        }
    }

    public void remove(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        final String SQL = "DELETE FROM Users WHERE user_id =?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public List<User> getUserByParameters(String condition) throws SQLException, DaoException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        final String MESSAGE = "Can't get user by id";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(condition);

            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
            return userList;

        } catch (SQLException e) {
            throw new DaoException(MESSAGE + condition, e);

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString(2));
        user.setSecondName(resultSet.getString(3));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setTelephoneNumber(resultSet.getString("phone_number"));
        user.setBalance(resultSet.getBigDecimal("balance"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        return user;
    }
}
