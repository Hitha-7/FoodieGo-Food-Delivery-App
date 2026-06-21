<%@ page import="com.tap.model.Cart"%>
<%@ page import="com.tap.model.CartItem"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>My Cart</title>

<link rel="stylesheet" href="css/cart.css">

</head>

<body>

<nav class="navbar">

<div class="logo">
    FoodieGo
</div>

<div class="nav-links">

    <a href="restaurants">Restaurants</a>
    <a href="cart.jsp">Cart</a>
    <a href="#">Orders</a>

</div>

</nav>

<div class="cart-wrapper">

<h1>My Cart</h1>

<%

Cart cart =
(Cart)session.getAttribute("cart");

if(cart != null &&
   !cart.getItems().isEmpty()){

%>

<div class="cart-container">

    <%

    for(CartItem item :
        cart.getItems()){

    %>

    <div class="cart-card">

        <div class="item-info">

            <h3>
              Rs.  <%=item.getItemName()%>
            </h3>

            <p>
                Quantity :
              Rs.  <%=item.getQuantity()%>
            </p>

        </div>

        <div class="item-price">

            Rs. <%=item.getTotalPrice()%>

        </div>

    </div>

    <%

    }

    %>

    <div class="summary">

        <h2>

            Grand Total :
            Rs. <%=cart.getTotalAmount()%>

        </h2>

        <a href="checkout.jsp"
           class="checkout-btn">

            Proceed To Checkout

        </a>

    </div>

</div>

<%

}
else{

%>

<div class="empty-cart">

    <h2>
        Your Cart is Empty 
    </h2>

    <p>
        Add delicious food from restaurants.
    </p>

    <a href="restaurants"
       class="shop-btn">

        Browse Restaurants

    </a>

</div>

<%

}

%>


</div>

</body>

</html>
