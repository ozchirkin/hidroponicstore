package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class testDaoUsed {
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "ac6947zka12o";

    public static void main(String args[]) throws DaoException, SQLException {

        User user1 = new User();

        user1.setUserId(1);
        user1.setFirstName("Vladick");
        user1.setSecondName("Cirilenco");
        user1.setRole(Role.CUSTOMER);
        user1.setLogin("drman");
        user1.setPassword("12345");
        user1.setEmail("Vladic@mail.ru");
        user1.setTelephoneNumber("87477934557");
        BigDecimal vladickBalance = new BigDecimal(350);
        user1.setBalance(vladickBalance);

        User user2 = new User();

        user2.setUserId(2);
        user2.setFirstName("Sanya");
        user2.setSecondName("Juma");
        user2.setRole(Role.CUSTOMER);
        user2.setLogin("Real_Pocan_95");
        user2.setPassword("12345");
        user2.setEmail("Real_Posan_95@mail.ru");
        user2.setTelephoneNumber("02#");
        BigDecimal balanceForJuma = new BigDecimal(300);
        user2.setBalance(balanceForJuma);

        UserDao userDao = new UserDao();

        Connection createdConnection = CreateConnection.getConnection();

        try {

            //  Добавление
            userDao.add(user1);
            userDao.add(user2);
      // Получение по ID
            User user3 = userDao.getUserById(2);
            System.out.println(user3.toString());
      // Удаление
            userDao.remove(user1);
            userDao.add(user1);
      // Докидываем владику на сосиску, тестим update
            BigDecimal addBalance = new BigDecimal(100);
            vladickBalance = vladickBalance.add(addBalance);
            user1.setBalance(vladickBalance);
            userDao.update(user1);
            System.out.println(userDao.getUserById(1));

       // Получение всех пользователей
            List<User> userList = userDao.getAll();
            System.out.println(userList);
      // Получение по параметрам

            List<User> userList1 = userDao.getUserByParameters("SELECT * FROM mydb.users WHERE role='CUSTOMER' AND balance>100");
            System.out.println(userList);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}


