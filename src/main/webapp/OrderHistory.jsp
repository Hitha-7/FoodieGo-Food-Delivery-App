<%@ page import="java.util.*" %>
<%@ page import="com.tap.model.OrderTable" %>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>My Orders</title>

<link rel="stylesheet"
href="css/OrderHistory.css">

</head>

<body>

<h1>My Orders</h1>

<div class="orders-container">

<%

List<OrderTable> orders =
(List<OrderTable>)
request.getAttribute("orders");

if(orders != null){

for(OrderTable order : orders){

%>

<div class="order-card">

<h2>
Order <%=order.getOrderId()%>
</h2>

<p>
Amount :
Rs. <%=order.getTotalAmount()%>
</p>

<p>
Status :
<%=order.getStatus()%>
</p>

<p>
Payment :
<%=order.getPaymentMode()%>
</p>

</div>

<%
}
}
%>

</div>

</body>

</html>
