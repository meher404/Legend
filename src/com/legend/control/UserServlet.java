package com.legend.control;

import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Bills;
import com.legend.lib.OrderDetails;
import com.legend.lib.Product;
import com.legend.lib.User;
import com.legend.lib.UserObject;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HashMap<Product, Integer> cart=new HashMap<Product, Integer>();
		ArrayList<Bills> bills=new ArrayList<Bills>();
		ArrayList<OrderDetails> orders=new ArrayList<OrderDetails>();
		UserObject userObj=new UserObject();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String email=request.getParameter("email");
		User user=new User();
		user=userObj.userObject(email);
		try {
			bills=user.getBills();
			orders=user.getOrders();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cart=user.getCart();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
