package com.tap;

import java.io.IOException;

import com.tap.daoImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class loginServlet extends HttpServlet {


@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

    String username =
            request.getParameter("username");

    String password =
            request.getParameter("password");

    UserDAOImpl dao =
            new UserDAOImpl();

    User user =
            dao.getUserByUsername(username);

    if(user != null &&
       user.getPassword().equals(password)) {

        HttpSession session =
                request.getSession();

        session.setAttribute(
                "user",
                user);

        response.sendRedirect(
                "restaurants");

    }
    else {

        request.setAttribute(
                "error",
                "Invalid Username or Password");

        request.getRequestDispatcher(
                "login.jsp")
                .forward(request, response);
    }
}

}
