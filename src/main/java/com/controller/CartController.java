package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.CartDTO;
import com.model.*;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {
	}

	/*
	 * Text payment So the: 9704198526191432198 Ngay phat hanh: 07/15 Ten: NGUYEN
	 * VAN A OTP: 123456
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextUrl = "WEB-INF/cart.jsp";
		Cart cart = new Cart();

		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "CART";
		}
		if (action.equals("ADD")) {
			// Add item to cart
			int cartId = (int) request.getSession().getAttribute("cartId");
			int productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cart.addItem(cartId, productId, quantity);
		} else if (action.equals("UPDATE")) {
			// Update item quantity
			int newQuantity = Integer.parseInt(request.getParameter("quantity"));
			int itemId = Integer.parseInt(request.getParameter("id"));
			new Cart().updateQuantity(itemId, newQuantity);
		} else if (action.equals("REMOVE")) {
			// Remove item from cart
			int itemId = Integer.parseInt(request.getParameter("id"));
			new Cart().removeItem(itemId);
		} else if (action.equals("Pay")) {

		}

		// update cart in cart page
		int userId = (int) request.getSession().getAttribute("userId");
		CartDTO cartDTO = cart.getUserCart(userId);
		request.setAttribute("cart", cartDTO);

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	// private void goPayment(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// String nextUrl = "WEB-INF/payment.jsp";
	// String uname = request.getParameter("username");
	// List<Cart> cart = cartDao.getCartList(uname);
	// request.setAttribute("cart_item", cart);
	// request.getRequestDispatcher(nextUrl).forward(request, response);
	// }

	// private void removeProductInCart(HttpServletRequest request,
	// HttpServletResponse response)
	// throws ServletException, IOException {
	// int productId = Integer.parseInt(request.getParameter("cId"));
	// cartDao.removeProduct(productId);
	// goPayment(request, response);

	// }
	// private void addProductToCart(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// String uname = request.getParameter("username");
	// String productId = request.getParameter("pId");
	// String quantityString = request.getParameter("quantity");
	// //User user = userDAO.getUserByUserName(uname);
	// Product product = null;

	// product = productDao.getProduct(Integer.parseInt(productId));
	// if(!cartDao.checkNameExist(product.codeProduct, uname)) {
	// Cart cart = new Cart(product.getCodeProduct(), uname,
	// product.getProductName(),
	// product.getNameAuthor(), Integer.parseInt(quantityString),
	// product.getPrice());

	// cartDao.addToCart(cart);

	// }

	// goPayment(request, response);
	// }
}
