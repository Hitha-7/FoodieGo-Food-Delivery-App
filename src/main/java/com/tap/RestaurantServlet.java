package com.tap;

import java.io.IOException;
import java.util.List;

import com.tap.daoImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RestaurantServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        RestaurantDAOImpl dao =
                new RestaurantDAOImpl();

        List<Restaurant> restaurants =
                dao.getAllRestaurants();

        request.setAttribute(
                "restaurants",
                restaurants);

        RequestDispatcher rd =
                request.getRequestDispatcher(
                        "restaurants.jsp");

        rd.forward(request, response);
    }
}