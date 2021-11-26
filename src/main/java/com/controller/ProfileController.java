package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.data.DAOs.*;
import com.model.*;


@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = null;
	private UserDAO userDao = null;

    public ProfileController() {
        super();
        userDao = new UserDAO();
        orderDAO = new OrderDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextUrl = "WEB-INF/profile.jsp";
		
		try {
		Object userId = request.getSession().getAttribute("userId");
		
		List<Item> items = null;
		// get current action
		String action = request.getParameter("action");

		List<Item> my_product = new ArrayList<Item>();
		if (action == null) {
			action = "Go to profile.jsp";
		}

		if (action.equals("SOMETHING")) {
			example();
		}
		User user = userDao.getUser(userId.toString());
		// get user to profile

		List<Order> list_order = orderDAO.getListOrderByUserId(userId.toString());
		// list the order of user
		for (Order order: list_order) {
			// list item in order by another user
			items = userDao.getMyBook(order.getId());
				
			// because items is list so we iterate, then add all them in my_product 
			for(Item item: items) my_product.add(item);
				
		}	
			
		request.setAttribute("list_item", my_product);
		request.setAttribute("user", user);

			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	private void example() {
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

}
