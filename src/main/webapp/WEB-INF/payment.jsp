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
	<div class="grid">
		<div class="grid_row">
			<div class="payment-block">
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
									src="https://kbimages1-a.akamaihd.net/625a6018-6751-4812-abfa-93c19d3fe0d8/140/215/60/False/lying-ways.jpg">
								<div>
									<p>It Ends with Us: A Novel</p>
									<span>Price: 95.000</span> <p>vnd</p> <br /> <a href="#">remove</a>
								</div>
							</div>
						</td>
						<td><input type="number" value="1" min="1"></td>
						<td>95.000 vnd</td>
					</tr>
					<tr>
						<td>
							<div class="cart-info">
								<img
									src="https://kbimages1-a.akamaihd.net/adff408e-a299-4d6c-a5fa-81aa587ce849/140/215/60/False/first-girl-to-die.jpg">
								<div>
									<p>I Will Teach You to Rich<p>
									<span>Price: 70.000</span> <p> vnd </p> <br /> <a href="#">remove</a>
								</div>
							</div>
						</td>
						<td><input type="number" value="1" min="1"></td>
						<td>70.000 vnd</td>
					</tr>
				</table>
		
				<div class="total-price">
					<table>
						<tr>
							<td>Total</td>
							<td>165.000 vnd</td>
						</tr>
					</table>
					<form action="./confirm">
						<button type="submit" value="" class="btn" style="width: 200px; height: 36px; align-items: center; margin: 10px 0; padding-top: 10px;">VN Pay</button>
					</form>
		
					
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
			</div>
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