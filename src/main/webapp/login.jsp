<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - FoodieGo</title>

<link rel="stylesheet" href="css/style.css">

<style>

body{
    background: linear-gradient(rgba(0,0,0,.75),
    rgba(0,0,0,.75)),
    url("images/hero-food.png");
    background-size: cover;
    background-position: center;
}

.form-box{
    backdrop-filter: blur(10px);
}

.subtitle{
    text-align:center;
    color:#cbd5e1;
    margin-bottom:20px;
    font-size:14px;
}

.register-link{
    text-align:center;
    margin-top:15px;
}

.register-link a{
    color:#ff6b35;
    text-decoration:none;
    font-weight:600;
}

.logo-text{
    text-align:center;
    color:#ff6b35;
    font-size:32px;
    font-weight:700;
    margin-bottom:10px;
}
.error{
    color:#ff4d6d;
    text-align:center;
    margin-bottom:15px;
    font-weight:600;
}

</style>

</head>

<body>

<div class="form-container">

    <div class="form-box">

        <div class="logo-text">
            FoodieGo
        </div>

        <h2>Welcome Back</h2>

        <p class="subtitle">
            Login to continue ordering your favorite food
        </p>
        <%
String error =
(String)request.getAttribute("error");

if(error != null){
%>
		
		<p style="color:red;
		          text-align:center;
		          margin-bottom:15px;">
		
		    <%=error%>
		
		</p>
		
<%
}
%>

        <form action="LoginServlet" method="post">

            <input
            type="text"
            name="username"
            placeholder="Username"
            required>

            <input
            type="password"
            name="password"
            placeholder="Password"
            required>

            <button type="submit">
                Login
            </button>

        </form>

        <div class="register-link">

            Don't have an account?

            <a href="registration.html">
                Register Now
            </a>

        </div>

    </div>

</div>

</body>
</html>