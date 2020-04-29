package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.*;

public class testDaoUsed {
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "ac6947zka12o";

    public static void main(String args[]) {
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
////        try {
////            connection = DriverManager.getConnection(url, user, password);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//
//        try {
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select * from Users");
//
//            while (resultSet.next()) {
//
//
//            User user = new User();
//            user.setUserId(resultSet.getInt("user_id"));
//            user.setFirstName(resultSet.getString("first_name"));
//            String us = user.getFirstName();
//                //System.out.println(us);
//        }
//
//           // resultSet.close();
//           // statement.close();
//           // connection.close();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        User user = new User();
        user.setFirstName("Vladick");
        user.setUserId(1);
        UserDao userDao = new UserDao();


        try {
            userDao.updateVladickName(user);
            User uuser= userDao.getUserById(1);


            String role = "ADMIN";
//            Role userRole = Role.valueOf(role);
            user.setRole(Role.valueOf(role));

            System.out.printf(user.getRole().toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
