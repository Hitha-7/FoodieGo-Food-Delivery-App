<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.tap.model.Restaurant" %>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>FoodieGo - Restaurants</title>

<link rel="stylesheet" href="css/restaurant.css">

</head>

<body>

<!-- NAVBAR -->

<nav class="navbar">


<div class="logo">
    FoodieGo
</div>

<div class="nav-links">

   <a href="restaurants">Restaurants</a>

<a href="OrderHistoryServlet">Orders</a>

<a href="offers.jsp">Offers</a>

<a href="contact.jsp">Contact</a>

<a href="Profile.jsp">Profile</a>

<a href="LogoutServlet">Logout</a>

</div>


</nav>

<!-- HERO SECTION -->

<section class="hero">


<div class="page-header">
    <h2>🍽 Restaurants Near You</h2>

   
</div>
<input
        type="text"
        id="searchInput"
        placeholder="Search Restaurants...">


</section>

<!-- RESTAURANTS -->

<section class="restaurants-section">


<h2>Popular Restaurants</h2>

<div class="restaurant-grid">

    <%

    List<Restaurant> restaurants =
    (List<Restaurant>)request.getAttribute("restaurants");

    if(restaurants != null){

    for(Restaurant r : restaurants){

    %>

    <div class="card"
         data-name="<%=r.getName()%>">

        <div class="card-image">

            <img src="<%=r.getImagePath()%>"
                 alt="<%=r.getName()%>">

        </div>

        <div class="card-body">

            <h3>
                <%=r.getName()%>
            </h3>

            <p class="cuisine">
                <%=r.getCuisineType()%>
            </p>

            <div class="details">

                <span class="rating">
                    ★ <%=r.getRating()%>
                </span>

                <span class="time">
                    🕒 <%=r.getDeliveryTime()%> mins
                </span>

            </div>

            <p class="offer">
                🔥 50% OFF up to ₹100
            </p>

            <p class="address">
                📍 <%=r.getAddress()%>
            </p>

            <a href="MenuServlet?restaurantId=<%=r.getRestaurantId()%>"
               class="menu-btn">

                View Menu

            </a>

        </div>

    </div>

    <%
        }
    }
    else{
    %>

    <h2>No Restaurants Available</h2>

    <%
    }
    %>

</div>


</section>

<!-- SEARCH FUNCTION -->

<script>

const searchInput =
document.getElementById("searchInput");

searchInput.addEventListener("keyup", () => {

    const value =
    searchInput.value.toLowerCase();

    const cards =
    document.querySelectorAll(".card");

    cards.forEach(card => {

        const restaurantName =
        card.dataset.name.toLowerCase();

        if(restaurantName.includes(value)){

            card.style.display = "block";
        }
        else{

            card.style.display = "none";
        }

    });

});

</script>

</body>

</html>
