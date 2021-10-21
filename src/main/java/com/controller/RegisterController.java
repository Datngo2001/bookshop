package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DTOs.RegisterDTO;
import com.data.accountDAO.RegisterDAO;
import com.model.User;
import com.services.HashService;

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

			try {
				if (doRegister(registerDTO, nextUrl, request, response)) {
					response.sendRedirect("home");
					return;
				}
				;
			} catch (ClassNotFoundException | IOException | ServletException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected boolean doRegister(RegisterDTO registerDTO, String nextUrl, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, SQLException, ClassNotFoundException {
		// Check not match password
		if (!registerDTO.getPassword().equals(registerDTO.getReEnter())) {
			// dispatch to register.jsp with a message
			SendErrorForRegisterPage(request, response, "Password not match");
			return false;
		}

		RegisterDAO registerDAO = new RegisterDAO();

		// Check existed user name
		if (registerDAO.isUsernameExisted(registerDTO.getUsername())) {
			// dispatch to register.jsp with a message
			SendErrorForRegisterPage(request, response, "Username is existed");
			return false;
		}

		HashService hashService = new HashService();

		byte[] salt = hashService.generateSalt();
		byte[] hash = null;

		// compute hash
		hash = hashService.doHash(registerDTO.getPassword().getBytes(), salt);

		User user = new User();
		user.setPasswordHash(hash);
		user.setPasswordSalt(salt);
		user.setUsername(registerDTO.getUsername());

		// do register
		if (registerDAO.register(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			return true;
		} else {
			// dispatch to register.jsp with a message
			SendErrorForRegisterPage(request, response, "Register server error");
			return false;
		}
	}

	protected void SendErrorForRegisterPage(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("registerMessage", message);
		request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
	}
}
