package com.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.OrderDAO;
import com.data.DAOs.UserDAO;
import com.model.Item;
import com.model.Order;
import com.model.User;

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
		Object userId = request.getSession().getAttribute("userId");
		List<Item> items = null;
		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "Go to profile.jsp";
		}

		if (action.equals("SOMETHING")) {
			example();
		}
		
		try {
			Order order = null;
			List<Order> list_order = orderDAO.getListOrderByUserId(userId.toString());
			for(Iterator<Order> o = list_order.iterator(); o.hasNext();) {
				order = o.next();
				items = userDao.getMyBook(order.getId());
			}
			request.setAttribute("items", items);
				
			
		} catch (Exception e) {
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
