package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = CreateConnection.getConnection();

    void add(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users(user_id,first_name,second_name,login,password,email,phone_number,balance,role) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getSecondName());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getTelephoneNumber());
            preparedStatement.setBigDecimal(8, user.getBalance());
            preparedStatement.setString(9, String.valueOf(Role.CUSTOMER));// как устанавливать роль если в таблеце она как ключь int а поле класса енум

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }
    }

    List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT user_id,first_name,second_name,login,password,email,phone_number,balance,role FROM USERS";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
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
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }

        return userList;
    }

    User getUserById(int id) throws SQLException, DaoException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM mydb.users WHERE user_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setSecondName(resultSet.getString("second_name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephoneNumber(resultSet.getString("phone_number"));
                user.setBalance(resultSet.getBigDecimal("balance"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                preparedStatement.close();
                //connection.close();

                return user;
            }

        } catch (SQLException e) {
            throw new DaoException("Can't get user by id" + id, e);
        }

        return null;
    }

    void update(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE USERS SET first_name=?,second_name=?,login=?,password=?,email=?,phone_number=?,balance=?,role=? WHERE user_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            // if (connection != null) {
            //   connection.close();
            //  }
        }
    }

    void remove(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Users WHERE user_id =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }
    }

    List<User> getUserByParameters(String condition) throws SQLException, DaoException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;

        String sql =   condition;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
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
                userList.add(user);
            }


            return userList;

        } catch (SQLException e) {
            throw new DaoException("Can't get user by id" + condition, e);
        }
        //return null; //не пойму почему если раскомментировать появляется ошибка: Delete unreachable statement

    }

}
