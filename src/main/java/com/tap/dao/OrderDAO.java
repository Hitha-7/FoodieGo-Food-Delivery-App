package com.tap.dao;

import java.util.List;
import com.tap.model.OrderTable;

public interface OrderDAO {

    public int addOrder(OrderTable order);

    OrderTable getOrderById(int id);

    List<OrderTable> getOrdersByUserId(int userId);

    boolean updateOrder(OrderTable order);

    boolean deleteOrder(int id);
}