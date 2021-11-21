package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review")
public class Review extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Review() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);

        String starsRating = req.getParameter("rating");
        String reviewContent = req.getParameter("review-content");

        System.out.println("Stars of user: " + starsRating);
        System.out.println("Review of user: " + reviewContent);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
