package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Seed;
import com.data.DAOs.ProductDAO;
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
				//new Seed().doSeed();
				request.setAttribute("username", request.getSession().getAttribute("username"));
				loadPopularOrder(request, response);
				loadTrendingBook(request, response);
				loadRomanceBook(request, response);
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

	private void loadRomanceBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getRomanceBook();
		request.setAttribute("romance_book", list_product);
		
	}

	private void loadTrendingBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getProducts(36);
		request.setAttribute("trending_book", list_product);
		
	}

	private void loadPopularOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getProducts(53);
		request.setAttribute("po_order", list_product);
	}

	private void detailProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String productId = null;
		Product product = null;

		try {
			productId = request.getParameter("productID");
			product = productDao.getProduct(Integer.parseInt(productId));
			
		} catch (Exception e) {
			log("productDao error", e);
		}

		request.setAttribute("product", product);
		String path = "/product?command=LOAD&id=" + request.getParameter("productID");
		// request.getRequestDispatcher("WEB-INF/productItem.jsp").forward(request,
		// response);
		response.sendRedirect(request.getContextPath() + path);
	}

	private void goHomePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> product = null;
		String action = request.getParameter("action");
		String next = request.getParameter("next");
		List<Product> list_product = productDao.getProducts();
		String sorting = request.getParameter("sort");
		if (sorting == null) sorting = "INC";
		int index;
		try {
			// set index
			if (next == null) index = 0;

			else index = Integer.parseInt(next);

			// get action to execute
			if (action == null) action = "";

			if (action.equals("PREV")) index--;

			else if (action.equals("NEXT")) index++;
			
			int limit = list_product.size() / 15;
			// when user next or prev over page available we set default for this case
			if (index < 0) index = 0;
			else if (index > limit - 1) index = limit;
			
			if(sorting.equals("INC")) {
				product = productDao.getProducts(index * 15);
				request.setAttribute("decrease", "INC");
			}
			else {
				product = productDao.getProductsDes(index * 15);
				request.setAttribute("decrease", "DES");
			}
			
			request.setAttribute("next", index);
			request.setAttribute("max", limit);
			
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
