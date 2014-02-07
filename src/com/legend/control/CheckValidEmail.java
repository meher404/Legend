package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.User;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class CheckValidEmail
 */
@WebServlet("/CheckValidEmail")
public class CheckValidEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		helpFunctions help=new helpFunctions();
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		try {
			if(help.checkExistingEmail(email)){
				System.out.println("email exists");
			}
			else{
				System.out.println("email doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
