package com.legend.lib;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


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
	public boolean insertOrders(User user) throws SQLException{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();

			HashMap<Product, Integer> cart=new HashMap<Product, Integer>();

			cart=user.getCart();
			String uid=user.getUserID();
			System.out.println();
			String saleid=helpFunctions.generateSaleid();
			for ( Product p : cart.keySet() ) {

				int quantity=cart.get(p);
				String pid=p.getPID();
				helpFunctions help=new helpFunctions();
				boolean validate=help.CheckProductQuantity(pid, quantity);
				if(!validate){
					return false;
				}

				prep = con.prepareStatement("insert into orderdetails values(?,?,?,?);");
				prep.setString(1,saleid);
				prep.setString(2, uid);
				prep.setString(3,pid);
				prep.setInt(4, quantity);
				prep.executeUpdate();
				help.updateProductQuantity(pid, quantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}



}
