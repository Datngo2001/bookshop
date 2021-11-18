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

		// get current action
		String action = request.getParameter("action");
		CartDTO cartDTO = (CartDTO) request.getSession().getAttribute("cart");

		if (action == null) {
			action = "CART";
		}
		switch (action) {
		case "CART":
			// Load Cart for user
			int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			request.getSession().setAttribute("cart", new Cart().getUserCart(userId));
			break;
		case "ADD":
			// add product to cart
			int productId = Integer.parseInt(request.getParameter("pId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			new Cart().addItem(cartDTO, productId, quantity);
			break;
		case "Pay":
			goPayment(request, response);
			break;
		case "REMOVE":
			int deleteId = Integer.parseInt(request.getParameter("id"));
			new Cart().removeItem(cartDTO, deleteId);
			break;
		case "UPDATE":
			int newQuantity = Integer.parseInt(request.getParameter("quantity"));
			int updateId = Integer.parseInt(request.getParameter("id"));
			new Cart().updateQuantity(cartDTO, updateId, newQuantity);
			break;
		default:
			// goPayment(request, response);
			break;
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);

	}

	private void goPayment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String nextUrl = "WEB-INF/payment.jsp";
		// String uname = request.getParameter("username");
		// List<Cart> cart = cartDao.getCartList(uname);
		// request.setAttribute("cart_item", cart);
		// request.getRequestDispatcher(nextUrl).forward(request, response);
	}

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
