package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.Address;
import com.legend.lib.MoreHelpFunctions;
import com.legend.lib.Shipping;
import com.legend.lib.User;

/**
 * Servlet implementation class ShippingServlet
 */
@WebServlet("/ShippingServlet")
public class ShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		Address add=new Address();
		Shipping ship=new Shipping();
		MoreHelpFunctions help=new MoreHelpFunctions();
		response.setContentType("text/html");
		int addId;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		String uid=user.getUserID();
		String recipientName=request.getParameter("your-name");
		String contactNumber=request.getParameter("your-phone");
		long contact=Long.parseLong(contactNumber);
		String doorno=request.getParameter("your-door-num");
		String street=request.getParameter("your-street");
		String city=request.getParameter("your-city");
		String state=request.getParameter("your-state");
		String pincode=request.getParameter("your-pin-code");
		long pin=Long.parseLong(pincode);
		addId=help.checkAddressId(doorno, street, city);
		if(addId==0){
			addId=add.generateAddressId(pin);
		}
		ship.setAddressid(addId);
		ship.setContactNo(contact);
		ship.setRecipient(recipientName);
		ship.setUserid(uid);
		ship.insertSaleid();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

}
