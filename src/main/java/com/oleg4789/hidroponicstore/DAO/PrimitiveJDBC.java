package com.oleg4789.hidroponicstore.DAO;

import java.sql.*;


public class PrimitiveJDBC {

    private static final String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "ac6947zka12o";
    private static Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public  void jdbcConnection(){

        Statement statement = null;

        System.out.println("MySQL JDBC тест соединения");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver не найден");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver зарегистрирован");


        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("устанавливаем соединение");

        } catch (SQLException e) {
            System.out.println("соединение не установленно");
            e.printStackTrace();
            return;
        }

        try {
            int res;
            statement = connection.createStatement();
            //String addUserOne = "INSERT INTO Users(user_id,email)  VALUES (1,'vladcir@maol.ru')";
            //statement.execute(addUserOne);
            //statement.executeUpdate("UPDATE Users SET phone_number = '87477934557', balance='300' where user_id =1;");
            statement.close();


        } catch (SQLException e) {
            //System.out.println("соединение не установленно");
            e.printStackTrace();
            return;
        } finally {
            try {
                if (connection != null) {
                    System.out.println("соединение установленно");
                    connection.close();
                    System.out.println("закрыли соединение");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("соединение не было закрыто");
            }
        }

    }
}


