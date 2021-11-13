package com.paypal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AuthorizePaymentServlet() {
        super();
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String action = request.getParameter("action");
    	if(action == null) {
    		action = "Check";
    	}
    	switch (action) {
		case "Check":
			loadCheckout(request, response);
			break;
		case "Pay":
			doCheckout(request, response);
		default:
			break;
		}
		
    }
	private void doCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String product = request.getParameter("product");
		String subtotal = request.getParameter("subtotal");
		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String total = request.getParameter("total");
		
		Checkout check = new Checkout(product, subtotal, shipping, tax, total);
		
		PaymentServices paymentServices = new PaymentServices();
		String approvalLink = "";
		try {
			approvalLink = paymentServices.authorizePayment(check);
			response.sendRedirect(approvalLink);
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("WEB-INF/paypal/paypalError.jsp").forward(request, response);
		}
	}
	private void loadCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/paypal/checkout.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
