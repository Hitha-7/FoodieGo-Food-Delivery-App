<%@ page import="com.tap.model.User" %>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Profile</title>

<link rel="stylesheet"
href="css/Profile.css">

</head>

<body>

<%

User user =
(User)session.getAttribute("user");

%>

<div class="profile-card">

<h1>My Profile</h1>

<p>
<b>Username :</b>
<%=user.getUsername()%>
</p>

<p>
<b>Email :</b>
<%=user.getEmail()%>
</p>

<p>
<b>Address :</b>
<%=user.getAddress()%>
</p>

<p>
<b>Role :</b>
<%=user.getRole()%>
</p>

<a href="login.jsp"
class="logout-btn">

Logout

</a>

</div>

</body>

</html>
