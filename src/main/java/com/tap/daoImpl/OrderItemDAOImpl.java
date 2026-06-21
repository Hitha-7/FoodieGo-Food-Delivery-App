package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private Connection connection;

    public OrderItemDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addOrderItem(OrderItem item) {

        String sql =
            "INSERT INTO OrderItem(orderId,menuId,quantity,itemTotal) VALUES(?,?,?,?)";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(sql);

            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getItemTotal());

            return pstmt.executeUpdate() > 0;

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

	@Override
	public OrderItem getOrderItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateOrderItem(OrderItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrderItem(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}