<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<!-- Global -->
<c:import url="sharedView/global.html" />

<link rel="stylesheet" href="./css/payment.css" />
<title>Your Cart</title>

</head>

<body>
	<c:import url="sharedView/header.jsp"></c:import>
	<div class="title">
		<h1 class="font-title">Shopping Cart</h1>
	</div>
	<div class="container-md cart">
		<table>
			<tr>
				<th>Product</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
			<tr>
				<td>
					<div class="cart-info">
						<img
							src="https://vn-live-05.slatic.net/p/a13d04587ee002cd8518ff1c95fab266.jpg_200x200q80.jpg_.webp">
						<div>
							<p>ASUS ROG Zephyrus Duo 15 GX550LWS-HF102T</p>
							<span>Price: 3000$</span> <br /> <a href="#">remove</a>
						</div>
					</div>
				</td>
				<td><input type="number" value="1" min="1"></td>
				<td>3000$</td>
			</tr>
			<tr>
				<td>
					<div class="cart-info">
						<img
							src="https://cdn.tgdd.vn/Products/Images/44/246077/lenovo-yoga-7-14itl5-i5-82bh00cjvn-600x600.jpg">
						<div>
							<p>Laptop Lenovo Yoga 9 14ITL5 i7</p>
							<span>Price: 2000$</span> <br /> <a href="#">remove</a>
						</div>
					</div>
				</td>
				<td><input type="number" value="1" min="1"></td>
				<td>2000$</td>
			</tr>
		</table>

		<div class="total-price">
			<table>
				<tr>
					<td>Total</td>
					<td>5000$</td>
				</tr>
			</table>
			<div id="smart-button-container">
				<div style="text-align: center;">
					<div id="paypal-button-container"></div>
				</div>
			</div>
		</div>
	</div>
  <div id="smart-button-container">
      <div style="text-align: center;">
        <div id="paypal-button-container"></div>
      </div>
    </div>
  <script src="https://www.paypal.com/sdk/js?client-id=AQ5g8nn9aROkHblV7Ljd5PJKV5Tr_cmh4WE6HrGy0GRCUyHOQKuQ7lOqMCdX9D31pAVSVcryYpROoTGY&enable-funding=venmo&currency=USD" data-sdk-integration-source="button-factory"></script>
  <script>
    function initPayPalButton() {
      paypal.Buttons({
        style: {
          shape: 'rect',
          color: 'gold',
          layout: 'vertical',
          label: 'paypal',
          
        },

        createOrder: function(data, actions) {
          return actions.order.create({
            purchase_units: [{"amount":{"currency_code":"USD","value":1}}]
          });
        },

        onApprove: function(data, actions) {
          return actions.order.capture().then(function(orderData) {
            
            // Full available details
            console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));

            // Show a success message within this page, e.g.
            const element = document.getElementById('paypal-button-container');
            element.innerHTML = '';
            element.innerHTML = '<h3>Thank you for your payment!</h3>';

            // Or go to another URL:  actions.redirect('thank_you.html');
            
          });
        },

        onError: function(err) {
          console.log(err);
        }
      }).render('#paypal-button-container');
    }
    initPayPalButton();
  </script>
		<c:import url="sharedView/footer.jsp"></c:import>
</body>
</html>