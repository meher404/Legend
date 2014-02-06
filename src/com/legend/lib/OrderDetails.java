package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OrderDetails {
	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static PreparedStatement prep;
	
	private static String saleid;
	private static String userId;
	private static String pid;
	private static int quantity;

	public OrderDetails(){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			saleid="sale001";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public static String getSaleid() {
		return saleid;
	}
	public static void setSaleid(String saleid) {
		OrderDetails.saleid = saleid;
	}
	public static String getUserId() {
		return userId;
	}
	public static void setUserId(String userId) {
		OrderDetails.userId = userId;
	}
	public static String getPid() {
		return pid;
	}
	public static void setPid(String pid) {
		OrderDetails.pid = pid;
	}
	public static int getQuantity() {
		return quantity;
	}
	public static void setQuantity(int quantity) {
		OrderDetails.quantity = quantity;
	}

	

	@SuppressWarnings("static-access")
	public static boolean insertOrders(Cart c) throws SQLException{
		userId=c.getUid();
		pid=c.getPid();
		quantity=c.getQuantity();
		helpFunctions help=new helpFunctions();
		boolean validate=help.CheckProductQuantity(pid, quantity);
		if(!validate){
			return false;
		}
		try {
			
			prep = con.prepareStatement("insert into orderdetails values(?,?,?,?);");
			prep.setString(1,saleid);
			prep.setString(2, userId);
			prep.setString(3,pid);
			prep.setInt(4, quantity);
			prep.executeUpdate();
			help.updateProductQuantity(pid, quantity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	

}
