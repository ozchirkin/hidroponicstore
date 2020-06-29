package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.Order;
import com.oleg4789.hidroponicstore.domain.Role;
import com.oleg4789.hidroponicstore.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao {
    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public void add(Order order) throws SQLException {
        PreparedStatement preparedStatement = null;
        final String SQL = "INSERT INTO Orders(order_id,date_of_create,sum,address,amount_stor_item,status_of_order,Delivery_type,Payment_type,Users_user_id) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            preparedStatement.setBigDecimal(3, order.getSum());
            preparedStatement.setString(4, order.getDeliveryAddress());
            preparedStatement.setInt(5, order.getAmountItems());
            preparedStatement.setString(6, String.valueOf(order.getOrderStatus()));
            preparedStatement.setString(7, String.valueOf(order.getDeliveryType()));
            preparedStatement.setString(8, String.valueOf(order.getPayType()));
            preparedStatement.setInt(9, order.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
