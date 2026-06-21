<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.tap.model.Menu" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Restaurant Menu</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/menu.css">

</head>

<body>

<nav class="navbar">

    <div class="logo">
        FoodieGo
    </div>

    <div class="nav-links">

        <a href="restaurants">Restaurants</a>
        <a href="cart.jsp">Cart</a>
        <a href="orders.jsp">Orders</a>

    </div>

</nav>

<section class="menu-section">

    <h1>🍽 Restaurant Menu</h1>

    <div class="menu-grid">

        <%

        List<Menu> menuList =
        (List<Menu>)request.getAttribute("menuList");

        if(menuList != null){

            for(Menu menu : menuList){

        %>

        <div class="menu-card">

            <img src="<%=menu.getImagePath()%>"
                 alt="<%=menu.getItemName()%>">

            <div class="menu-content">

                <h3>
                    <%=menu.getItemName()%>
                </h3>

                <p>
                    <%=menu.getDescription()%>
                </p>

                <div class="bottom">

                    <span class="price">
                        ₹ <%=menu.getPrice()%>
                    </span>

                    <form action="AddToCartServlet"
                          method="post">

                        <input type="hidden"
                               name="menuId"
                               value="<%=menu.getMenuId()%>">

                        <button type="submit">
                            Add To Cart
                        </button>

                    </form>

                </div>

            </div>

        </div>

        <%
            }
        }
        %>

    </div>

</section>

</body>

</html>