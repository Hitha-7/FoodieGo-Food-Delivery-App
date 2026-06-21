<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Checkout</title>

<link rel="stylesheet" href="css/checkout.css">

</head>

<body>

<div class="checkout-container">

    <h1>Checkout</h1>

    <form action="PlaceOrderServlet"
          method="post">

        <label>
            Delivery Address
        </label>

        <textarea
            name="address"
            required
            placeholder="Enter Delivery Address"></textarea>

        <label>
            Payment Method
        </label>

        <select name="paymentMode">

            <option value="COD">
                Cash On Delivery
            </option>

            <option value="UPI">
                UPI
            </option>

            <option value="CARD">
                Card
            </option>

        </select>

        <button type="submit">

            Place Order

        </button>

    </form>

</div>

</body>

</html>