package com.oleg4789.hidroponicstore.Service;

import com.oleg4789.hidroponicstore.DAO.ConnectionCreator;
import com.oleg4789.hidroponicstore.DAO.DaoException;
import com.oleg4789.hidroponicstore.DAO.UserDao;
import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;


public class UserService {
    public Connection connection;
    public UserDao userDao;

    public UserService() {
        connection = ConnectionCreator.getConnection();
        UserDao userDao = new UserDao(connection);
        this.userDao = userDao;
    }


    public void registrationNewUser(String[] formArray) throws SQLException {

        User user = new User();

        user.setFirstName(formArray[1]);
        user.setSecondName(formArray[2]);
        user.setRole(Role.valueOf(formArray[3]));
        user.setLogin(formArray[4]);
        user.setPassword(formArray[5]);
        user.setEmail(formArray[6]);
        user.setTelephoneNumber(formArray[7]);

        userDao.add(user);
    }

    public User userLogin(String login, String password) throws SQLException, DaoException {
        User user = userDao.getUserByLogin(login);

        if(user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void userUpdate(User user) throws SQLException {

        userDao.update(user);
    }

    public User getUserById(int id) throws SQLException, DaoException {

        return userDao.getUserById(id);
    }

    public void userUpdateCash(int userId, BigDecimal sum, boolean target) throws SQLException {

        userDao.updateUserCash(userId, sum, target);
    }
}
