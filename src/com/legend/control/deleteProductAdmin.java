package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.deleteProduct;

/**
 * Servlet implementation class deleteProductAdmin
 */
@WebServlet("/deleteProductAdmin")
public class deleteProductAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		deleteProduct delete=new deleteProduct(); 
		response.setContentType("text/html");
		String pid=request.getParameter("pid");
		delete.deleteProductAdmin(pid);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
