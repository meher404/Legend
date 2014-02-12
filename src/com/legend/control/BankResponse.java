package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.OrderDetails;
import com.legend.lib.User;


@WebServlet("/BankResponse")
public class BankResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		OrderDetails order=new OrderDetails();
		response.setContentType("text/html");
		HttpSession ss= request.getSession();
		User user=(User) ss.getAttribute("user");
		String str="";
		String status=request.getParameter("status");
		if(status.equals("1")){
			//str=order.insertOrders(user,addressId);
		}
		else if(status.equals("2")){
			out.println("Insufficient Funds Transaction Failed");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
