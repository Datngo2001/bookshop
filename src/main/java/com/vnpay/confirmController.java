package com.vnpay;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.PromoDAO;
import com.model.Cart;
import com.model.Promo;


@WebServlet("/confirm")
public class confirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PromoDAO promoDao = null;
    public confirmController() {
        super();
        promoDao = new PromoDAO();
 
    }
    private static final DecimalFormat df = new DecimalFormat("0");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextUrl = "WEB-INF/vnpay/index_vnpay.jsp";
		
		double price = Double.parseDouble(request.getParameter("price"));
		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "";
			
		}

		request.setAttribute("priceTotal", df.format(price));
		if(action.equals("CHECK")) {
			pricePromoCode(request, response);
			nextUrl = "WEB-INF/vnpay/index_vnpay.jsp";
		}
		else if(action.equals("BUYNOW")) {
			Cart cart = new Cart();
			int cartId = (int) request.getSession().getAttribute("cartId");
			int productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cart.addItem(cartId, productId, quantity);
		}
		try {
			int userId = (int) request.getSession().getAttribute("userId");
		} catch (Exception e) {
			response.sendRedirect("login");
			return;
		}

		
		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	private void pricePromoCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("promoCode");
		int amount = Integer.parseInt(request.getParameter("amount"));
		Promo promo = promoDao.getPromoCode(code);
		try {
			if(promo != null)  {
				int priceDiscount = 0;
				if(Integer.parseInt(promo.getTypeCode()) == 1) {
					priceDiscount = amount - (int) promo.getValue();
				}
				else {
					priceDiscount = amount -  (amount *(int) promo.getValue());
				}
				request.setAttribute("priceTotal", priceDiscount);
				request.getSession().setAttribute("check", "OK");
				}
		}
		catch (Exception e) {
			response.sendRedirect("confirm");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
