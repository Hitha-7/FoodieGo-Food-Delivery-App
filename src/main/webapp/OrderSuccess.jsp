<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Order Success</title>

<link rel="stylesheet" href="css/OrderSuccess.css">

</head>

<body>

<div class="success-container">


<div class="success-card">

    <div class="tick">✓</div>

    <h1>Order Placed Successfully</h1>

    <p>
        Your food is being prepared.
    </p>

    <h3>
        Order ID:
        <%= request.getAttribute("orderId") %>
    </h3>

    <p>
        Estimated Delivery:
        30 Minutes
    </p>

    <a href="restaurants"
       class="home-btn">

        Continue Shopping

    </a>

</div>

</div>

</body>

</html>
