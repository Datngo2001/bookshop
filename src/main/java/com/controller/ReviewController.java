package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.ReviewDAO;
import com.data.DAOs.UserDAO;
import com.model.Product;
import com.model.Review;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReviewController() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null) {
            action = "Go to productItem.jsp";
        }

        if (action.equals("CREATE")) {
            UserDAO userDAO = new UserDAO();

            String productId = req.getParameter("productId");
            int starsRating = req.getParameter("rating") == null ? 1 : Integer.parseInt(req.getParameter("rating"));
            String reviewContent = req.getParameter("review-content");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String date = formatter.format(new Date());
            String userId = req.getSession().getAttribute("userId").toString();

            if (productId == null || userId == null) {
                req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, res);
                return;
            }

            Review review = new Review(date, reviewContent, userDAO.getUser(userId),
                    Product.find(Integer.parseInt(productId)), starsRating);

            System.out.println(review.getContent());

            ReviewDAO reviewDAO = new ReviewDAO();
            if (reviewDAO.addReview(review) != null) {
                Product product = Product.find(Integer.parseInt(productId));
                req.setAttribute("product", product);
                System.out.println("Create review success!!!");

            } else {
                System.out.println("Error when trying to create new review!!!");
            }
        }

        // req.getRequestDispatcher(path)
        req.getRequestDispatcher("WEB-INF/productItem.jsp").forward(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
