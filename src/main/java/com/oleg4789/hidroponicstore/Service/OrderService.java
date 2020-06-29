package com.oleg4789.hidroponicstore.Service;

import com.oleg4789.hidroponicstore.DAO.ConnectionCreator;
import com.oleg4789.hidroponicstore.DAO.DaoException;
import com.oleg4789.hidroponicstore.DAO.OrderDao;
import com.oleg4789.hidroponicstore.DAO.UserDao;
import com.oleg4789.hidroponicstore.domain.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderService {
    public Connection connection;
    public OrderDao orderDao;
    public  UserDao userDao;

    public OrderService() {
        connection = ConnectionCreator.getConnection();
        OrderDao orderDao = new OrderDao(connection);
        this.orderDao = orderDao;
        UserDao userDao = new UserDao(connection);
        this.userDao = userDao;
    }

    public void addOrder(int userId, BigDecimal sum, PaymentType payType, Status stat, String deliveryAddress, DeliveryType deliveryType, List<StorageItem> storageItems, int amountItem) throws SQLException, DaoException {
        User user = userDao.getUserById(userId);

        Order order =new Order();

        order.setOrderDate(new Date());
        order.setSum(sum);
        order.setPayType(payType);
        order.setOrderStatus(stat);
        order.setDeliveryAddress(deliveryAddress);
        order.setUserId(userId);
        order.setDeliveryType(deliveryType);
        order.setStorageItems(storageItems);
        order.setAmountItems(amountItem);

        orderDao.add(order);

        user.setBalance((user.getBalance().subtract(sum)));
        userDao.update(user);

    }
}
