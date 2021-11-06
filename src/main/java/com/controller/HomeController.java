package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.ProductDAO;
import com.model.Product;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDao;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			productDao = new ProductDAO();
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	public HomeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");
			if (theCommand == null) {
				theCommand = "HOME";
			}
			switch (theCommand) {
			case "HOME":
				//addProducts(request, response);
				//removeProduct(request, response);
				goHomePage(request, response);
				break;
			case "LOAD":
				detailProduct(request, response);
				break;
			default:
				goHomePage(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// private void removeProduct(HttpServletRequest request, HttpServletResponse response) {
	// 	productDao.deleteProduct(60);
	// }

	// private void addProducts(HttpServletRequest request, HttpServletResponse response) {
		
	// 	Product product = new Product("Thien Nguyen", "Have a nice day", 2000, "Classic");
	// 	Product product1 = new Product("Dat Ngo", "Go to the moon", 3000, "Romance");
	// 	Product product2 = new Product("Duong le", "Meme", 2000, "Comedy");
	// 	Product product3 = new Product("Dat Tran", "Good job", 2500, "Buisiness");
	// 	Product product4 = new Product("Thuy Nguyen", "Go to the moon", 3500, "Romance");
	// 	productDao.addProducts(product);
	// 	productDao.addProducts(product1);
	// 	productDao.addProducts(product2);
	// 	productDao.addProducts(product3);
	// 	productDao.addProducts(product4);
	// }

	private void detailProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String productId = null;
		Product product = null;
		try {
			productId = request.getParameter("productID");
			product = productDao.getProduct(productId);
		} catch (Exception e) {
			log("productDao error", e);
		}
		request.setAttribute("theProduct", product);
		request.getRequestDispatcher("WEB-INF/productItem.jsp").forward(request, response);
	}

	private void goHomePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> product = null;
		try {
			product = productDao.getProducts();
		} catch (Exception e) {
			log("productDao error", e);
		}
		request.setAttribute("list_product", product);
		request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
