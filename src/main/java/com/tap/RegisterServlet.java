package com.tap;

import java.io.IOException;
import java.time.LocalDateTime;

import com.tap.daoImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if (!password.equals(confirmPassword)) {

        	request.setAttribute(
        		    "error",
        		    "Passwords do not match");

        		request.getRequestDispatcher(
        		    "registration.jsp")
        		    .forward(request,response);
            return;
        }

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAddress(address);

        // Default values
        user.setRole("CUSTOMER");
        user.setCreatedDate(LocalDateTime.now());
        user.setLastLoginDate(LocalDateTime.now());

        UserDAOImpl dao = new UserDAOImpl();

        boolean status = dao.addUser(user);

        if (status) {
            response.sendRedirect("login.jsp");
        } else {
            response.getWriter().println("Registration Failed!");
        }
    }
}