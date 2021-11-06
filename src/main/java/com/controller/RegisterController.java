package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.RegisterDTO;
import com.model.User;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextUrl = "WEB-INF/register.jsp";

		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "Go to register.jsp";
		}

		if (action.equals("REGISTER")) {
			RegisterDTO registerDTO = new RegisterDTO();
			registerDTO.setUsername(request.getParameter("username"));
			registerDTO.setPassword(request.getParameter("password"));
			registerDTO.setReEnter(request.getParameter("reEnter"));

			if (new User().register(registerDTO)) {
				request.getSession().setAttribute("username", registerDTO.getUsername());
				response.sendRedirect("home");
				return;
			} else {
				request.setAttribute("registerMessage", registerDTO.getErrorMessage());
				nextUrl = "WEB-INF/register.jsp";
			}
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
