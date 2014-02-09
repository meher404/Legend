package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.helpFunctions;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@SuppressWarnings({ "static-access", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email,password;
		email=request.getParameter("your-email");
		password=request.getParameter("pass_confirmation");
		helpFunctions help=new helpFunctions();
		HttpSession ss= request.getSession();
		ss.setAttribute("email", email);
		if(help.checkStatus(email)){
			if(help.Login(email, password)){
				System.out.println("Login Success");
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
			else{
				System.out.println("failed");
				HttpSession ss1=request.getSession(false);
				ss1.invalidate();
			}
		}
		else{
			System.out.println("Not-Active");
			RequestDispatcher rd=request.getRequestDispatcher("activate.html");
			rd.include(request, response);
		}

	}

}