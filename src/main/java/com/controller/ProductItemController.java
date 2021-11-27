package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.ProductDAO;
import com.data.DAOs.ReviewDAO;
import com.model.Product;
import com.model.Review;

@WebServlet("/product")
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

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nextUrl = "WEB-INF/productItem.jsp";
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");

		// get current action
		try {
			String action = req.getParameter("command");

			if (action == null) {
				action = "Go to productList.jsp";
			}

			switch (action) {
			case "LOAD":
				loadProductItem(req, res);
				loadReviews(req, res);
				break;
			default:
				loadProductItem(req, res);
			}

			req.getRequestDispatcher(nextUrl).forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadProductItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = 0;
		Product product = null;

		try {
			id = Integer.parseInt(request.getParameter("id"));
			product = Product.find(id);

			if (product == null) {
				request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				return;
			}

			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadReviews(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Review> reviews = null;
		ReviewDAO reviewDAO = new ReviewDAO();
		String productId = req.getParameter("id");
		float averageStar = 0;

		try {
			reviews = reviewDAO.getReviews(productId);
			int sumStar = 0;
			for (Review review : reviews) {
				sumStar += review.getStars();
			}
			averageStar = sumStar == 0 ? sumStar : sumStar / reviews.size();
		} catch (Exception e) {
			System.out.println("reviewDAO error!" + e);
			log("reviewDAO error!", e);
		}

		req.setAttribute("reviews", reviews);
		req.setAttribute("averageStar", averageStar);
		req.setAttribute("averageStarInt", Math.round(averageStar));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
