package com.tap;

import java.io.IOException;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.daoImpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId =
                Integer.parseInt(
                        request.getParameter("restaurantId"));

        MenuDAO menuDAO =
                new MenuDAOImpl();

        List<Menu> menuList =
                menuDAO.getMenuByRestaurantId(
                        restaurantId);

        request.setAttribute(
                "menuList",
                menuList);

        request.getRequestDispatcher(
                "menu.jsp")
                .forward(request, response);
    }
}