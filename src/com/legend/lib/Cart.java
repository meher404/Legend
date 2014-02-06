package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Cart {

	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static PreparedStatement prep;	

	public Cart(){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getUid() {
		return uid;
	}

	public static void setUid(String uid) {
		Cart.uid = uid;
	}

	public static String getPid() {
		return pid;
	}

	public static void setPid(String pid) {
		Cart.pid = pid;
	}

	private static int quantity;
	private static String uid;
	private static String pid;

	static boolean insertCart(Product p){
		try {
			boolean validate;
			pid=p.getPID();
			validate=helpFunctions.CheckProductQuantity(pid, quantity);
			if(!validate){
				return false;
			}
			prep = con.prepareStatement("insert into cart values(?,?,?);");
			prep.setString(1,uid);
			prep.setString(2,pid);
			prep.setInt(3,quantity);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	static boolean updateCart(int number) throws SQLException{
		boolean validate=helpFunctions.CheckProductQuantity(getPid(), number);
		if(!validate){
			return false;
		}
		rs=st.executeQuery("select quantity from cart where userID='"+uid+"' and pid='"+pid+"';");
		int quan=rs.getInt("quantity");
		quan=quan+number;
		st.executeUpdate("update cart set quantity='"+quan+"' where userID='"+uid+"' and pid='"+pid+"';");
		return true;
	}

	public static int getQuantity() {
		return quantity;
	}

	public static void setQuantity(int quantity) {
		Cart.quantity = quantity;
	}


}
