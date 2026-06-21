package com.tap;

import java.io.IOException;

import com.tap.dao.MenuDAO;
import com.tap.daoImpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int menuId =
                Integer.parseInt(
                        request.getParameter("menuId"));

        MenuDAO dao =
                new MenuDAOImpl();

        Menu menu =
                dao.getMenuById(menuId);

        HttpSession session =
                request.getSession();

        Cart cart =
                (Cart) session.getAttribute("cart");

        if(cart == null) {

            cart = new Cart();
        }

        CartItem item =
                new CartItem(
                        menu.getMenuId(),
                        menu.getItemName(),
                        menu.getPrice(),
                        1
                );

        cart.addItem(item);

        session.setAttribute(
                "cart",
                cart);

        response.sendRedirect(
                "cart.jsp");
    }
}