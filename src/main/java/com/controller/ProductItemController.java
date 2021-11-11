package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.ProductDAO;
import com.model.Product;

@WebServlet("/productItem")
public class ProductItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			productDAO = new ProductDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public ProductItemController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextUrl = "WEB-INF/productItem.jsp";
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		// get current action
		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Product product = productDAO.getProduct(id);
			if (product == null) {
				request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				return;
			}

			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (action == null) {
			action = "Go to productList.jsp";
		}

		if (action.equals("SOMETHING")) {
			example();
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	private void example() {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
