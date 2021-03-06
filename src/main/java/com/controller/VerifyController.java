package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.DTOs.BusinessDtos.RegisterDTO;
import com.model.User;

@SuppressWarnings("serial")
@WebServlet("/verify")
public class VerifyController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextUrl = "WEB-INF/verify.jsp";

        // get current action
        String action = request.getParameter("action");

        if (action == null) {
            action = "Go to verify.jsp";
        }

        if (action.equals("VERIFY")) {
        	String code = request.getParameter("code");
        	
        	RegisterDTO registerDTO =  (RegisterDTO) request.getSession().getAttribute("register");
        	registerDTO.setCode(code);
            if (new User().verify(registerDTO, code)) {
                request.getSession().setAttribute("username", registerDTO.getUsername());
                request.getSession().setAttribute("role", registerDTO.getRole());
                request.getSession().setAttribute("roleId", registerDTO.getRoleId());

                response.sendRedirect("home");
                return;
            } else {
                request.setAttribute("verifyMessage", registerDTO.getErrorMessage());
            }
        }

        request.getRequestDispatcher(nextUrl).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}