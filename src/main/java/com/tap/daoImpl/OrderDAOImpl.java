package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;



import com.tap.dao.OrderDAO;
import com.tap.model.OrderTable;
import com.tap.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;

    public OrderDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public List<OrderTable> getOrdersByUserId(int userId) {

        List<OrderTable> orders =
                new ArrayList<>();

        String sql =
                "SELECT * FROM OrderTable WHERE userId=?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(sql);

            pstmt.setInt(1, userId);

            ResultSet rs =
                    pstmt.executeQuery();

            while(rs.next()) {

                OrderTable order =
                        new OrderTable();

                order.setOrderId(
                        rs.getInt("orderId"));

                order.setUserId(
                        rs.getInt("userId"));

                order.setTotalAmount(
                        rs.getDouble("totalAmount"));

                order.setStatus(
                        rs.getString("status"));

                order.setPaymentMode(
                        rs.getString("paymentMode"));

                orders.add(order);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orders;
    }


	@Override
	public int addOrder(OrderTable order) {
		// TODO Auto-generated method stub
		
		String sql =
		"INSERT INTO OrderTable(userId,totalAmount,status,paymentMode) VALUES(?,?,?,?)";

		try {

		    PreparedStatement pstmt =
		    connection.prepareStatement(
		            sql,
		            Statement.RETURN_GENERATED_KEYS);

		    pstmt.setInt(1, order.getUserId());
		    pstmt.setDouble(2, order.getTotalAmount());
		    pstmt.setString(3, order.getStatus());
		    pstmt.setString(4, order.getPaymentMode());

		    int rows = pstmt.executeUpdate();

		    if(rows > 0) {

		        ResultSet rs =
		        pstmt.getGeneratedKeys();

		        if(rs.next()) {

		            return rs.getInt(1);
		        }
		    }

		}
		catch(Exception e) {

		    e.printStackTrace();
		}

		return 0;
		

		}

	



	@Override
	public OrderTable getOrderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean updateOrder(OrderTable order) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean deleteOrder(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}