package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.DAOs.CartDao;
import com.data.DAOs.ProductDAO;
import com.model.CardList;

import com.model.Product;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDao;
	private CartDao cartDao;
    public PaymentController() {
    	productDao = new ProductDAO();
    	cartDao = new CartDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	private void goPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextUrl = "WEB-INF/payment.jsp";
		int uid = Integer.parseInt(request.getParameter("uId"));
		List<CardList> cart = cartDao.getCartList(uid);
		request.setAttribute("cart_item", cart);
		request.getRequestDispatcher(nextUrl).forward(request, response);

		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// get current action
			String action = request.getParameter("action");

			if (action == null) {
				action = "Pay";
			}
			switch (action) {
			case "ADD":
				addProductToCart(request, response);
				break;
			case "Pay":
				goPayment(request, response);
				break;
			case "REMOVE":
				removeProductInCart(request, response);
				break;
			default:
				goPayment(request, response);
				break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void removeProductInCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("cId"));
        cartDao.removeProduct(productId);
        double price = Double.parseDouble(request.getParameter("price"));
        request.setAttribute("total", price);
        goPayment(request, response);
		
	}

	private void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("uId"));
		double price = Double.parseDouble(request.getParameter("price"));
        String productId = request.getParameter("pId");
        String quantityString = request.getParameter("quantity");
        double total = 0;
        Product product = null;
        product = productDao.getProduct(Integer.parseInt(productId));
        if(!cartDao.checkNameExist(product.getDescription(), uid)) {
            CardList cart = new CardList(uid, product.getDescription(), 
            		product.getNameAuthor(), Integer.parseInt(quantityString), product.getPrice());
            cartDao.addToCart(cart);
            total +=cart.getPrice();
        }

        request.setAttribute("total", total);
        goPayment(request, response);
	}



	
}
