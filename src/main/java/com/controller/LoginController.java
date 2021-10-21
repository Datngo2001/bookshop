package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DTOs.LoginDTO;
import com.data.accountDAO.LoginDAO;
import com.model.User;
import com.services.HashService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextUrl = "WEB-INF/login.jsp";

		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "Go to Login.jsp";
		}
		
		if (action.equals("LOGIN")) {
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUsername(request.getParameter("username"));
			loginDTO.setPassword(request.getParameter("password"));
			try {
				if(doLogin(loginDTO, nextUrl, request, response)) {
					response.sendRedirect("home");
					return;
				};
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected boolean doLogin(LoginDTO loginDTO, String nextUrl, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException {
		LoginDAO loginDAO = new LoginDAO();

		User user = loginDAO.loadHashAndSalt(loginDTO.getUsername());
		
		if(user == null) {
			request.setAttribute("loginMessage", "Password or username incorrect");
			request.getRequestDispatcher(nextUrl).forward(request, response);
			return false;
		}

		// compute hash
		HashService hashService = new HashService();
		byte[] hashedInputPass = hashService.doHash(loginDTO.getPassword().getBytes(), user.getPasswordSalt());

		// compare hash result with the hash from database
		boolean matched = Arrays.equals(hashedInputPass, user.getPasswordHash());

		if (matched) {
			HttpSession session = request.getSession();
			session.setAttribute("username", loginDTO.getUsername());
			return true;
		} else {
			request.setAttribute("loginMessage", "Password or username incorrect");
			request.getRequestDispatcher(nextUrl).forward(request, response);
			return false;
		}
	}
}
