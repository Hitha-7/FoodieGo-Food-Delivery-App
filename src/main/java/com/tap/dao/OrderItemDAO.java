package com.tap.dao;

import java.util.List;
import com.tap.model.OrderItem;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem item);

    OrderItem getOrderItemById(int id);

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    boolean updateOrderItem(OrderItem item);

    boolean deleteOrderItem(int id);
}