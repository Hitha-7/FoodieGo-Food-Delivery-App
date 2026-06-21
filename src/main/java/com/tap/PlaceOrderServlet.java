package com.tap;

import java.io.IOException;

import com.tap.dao.OrderDAO;
import com.tap.dao.OrderItemDAO;
import com.tap.daoImpl.OrderDAOImpl;
import com.tap.daoImpl.OrderItemDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.OrderItem;
import com.tap.model.OrderTable;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();
        System.out.println("Session User = " +
                session.getAttribute("user"));

        Cart cart =
                (Cart)session.getAttribute("cart");

        User user =
                (User)session.getAttribute("user");

        String paymentMode =
                request.getParameter("paymentMode");

        if(cart == null ||
           cart.getItems().isEmpty()) {

            response.sendRedirect("cart.jsp");
            return;
        }

        OrderTable order =
                new OrderTable();

        order.setUserId(1);

        order.setTotalAmount(
                cart.getTotalAmount());

        order.setStatus(
                "Pending");

        order.setPaymentMode(
                paymentMode);

        OrderDAO orderDAO =
                new OrderDAOImpl();

        int orderId =
                orderDAO.addOrder(order);

        OrderItemDAO itemDAO =
                new OrderItemDAOImpl();

        for(CartItem item :
                cart.getItems()) {

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setOrderId(
                    orderId);

            orderItem.setMenuId(
                    item.getMenuId());

            orderItem.setQuantity(
                    item.getQuantity());

            orderItem.setItemTotal(
                    item.getTotalPrice());

            itemDAO.addOrderItem(
                    orderItem);
        }

        System.out.println("Order ID = " + orderId);

        if(orderId > 0) {
            session.removeAttribute("cart");
        }

        request.setAttribute(
                "orderId",
                orderId);

        request.getRequestDispatcher(
                "OrderSuccess.jsp")
                .forward(request,response);
    }
}