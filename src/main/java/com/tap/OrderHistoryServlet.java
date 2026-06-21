package com.tap;

import java.io.IOException;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.daoImpl.OrderDAOImpl;
import com.tap.model.OrderTable;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {


@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session =
            request.getSession();

    User user =
            (User)session.getAttribute("user");

    OrderDAO dao =
            new OrderDAOImpl();
    ;

    List<OrderTable> orders =
            dao.getOrdersByUserId(user.getUserId());

    System.out.println("Orders Found = " +
                       orders.size());

    request.setAttribute(
            "orders",
            orders);

    request.getRequestDispatcher(
            "OrderHistory.jsp")
            .forward(request,response);
}


}
