package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.CartDTO;
import com.DTOs.BusinessDtos.LoginDTO;
import com.data.DAOs.UserDAO;
import com.model.Cart;
import com.model.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDao = null;

	public LoginController() {
		super();
		userDao = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextUrl = "WEB-INF/login.jsp";
		String url = "";

		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "Go to Login.jsp";
		}

		if (action.equals("LOGIN")) {

			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUsername(request.getParameter("username"));
			loginDTO.setPassword(request.getParameter("password"));
			if (new User().login(loginDTO)) {
				// get username and id
				request.getSession().setAttribute("username", loginDTO.getUsername());

				request.getSession().setAttribute("id", loginDTO.getId());
				//take role id of user
				
				int rid = loginDTO.getRoleId();
				
				if (rid == 3) {
					url = "home";
				}

				else if (rid == 1 || rid == 2) {
					request.getSession().setAttribute("rid", rid);
					url = "admin/product";
				}

				// Load Cart for user
				CartDTO cartDTO = new Cart().getUserCart(loginDTO.getId());
				request.getSession().setAttribute("cart", cartDTO);
				response.sendRedirect(url);
				return;
			} else {
				request.setAttribute("loginMessage", "Password or username incorrect");
			}
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
