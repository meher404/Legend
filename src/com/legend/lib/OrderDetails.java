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
	
	private String saleid;
	private String userId;
	private String pid;
	private int quantity;

	public OrderDetails(){
		saleid="sale001";
	}

	

	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	@SuppressWarnings("static-access")
	public boolean insertOrders(Cart c) throws SQLException{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
