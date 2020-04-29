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
        String sql = "INSERT INTO Users(user_id,first_name,second_name,login,pasword,email,phone_number,balance,Role_role_id) " +
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
            preparedStatement.setInt(9, 2);// как устанавливать роль если в таблеце она как ключь int а поле класса енум
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT user_id,first_name,second_name,login,pasword,email,phone_number,balance,Role_role_id FROM USERS";
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
                user.setPassword(resultSet.getString("pasword"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephoneNumber(resultSet.getString("phone_number"));
                user.setBalance(resultSet.getBigDecimal("balance"));
                //user.setRole(resultSet.get);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return userList;
    }

    User getUserById(int id) throws SQLException {
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
                user.setPassword(resultSet.getString("pasword"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephoneNumber(resultSet.getString("phone_number"));
                user.setBalance(resultSet.getBigDecimal("balance"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return null;
    }

    void update(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE USERS SET first_name=?,second_name=?,login=?,pasword=?,email=?,phone_number=?,balance=?,Role_role_id=? WHERE user_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelephoneNumber());
            preparedStatement.setBigDecimal(7, user.getBalance());
            preparedStatement.setInt(8, 2);
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
            if (connection != null) {
                connection.close();
            }
        }
    }

    User getUserByParameters() {
        return null;
    }


    void updateVladickName(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE USERS SET first_name=? WHERE user_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getFirstName());

            preparedStatement.setInt(2, user.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
        }
    }


}
