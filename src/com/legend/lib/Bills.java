package com.legend.lib;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Bills {
	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static PreparedStatement prep;	

	public Bills(){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static String saleid;
	private static int addressID;
	private static java.util.Date timeStamp;
	private static double totalAmount;
	private static String DeliveryStatus;

	public static String getSaleid() {
		return saleid;
	}
	public static void setSaleid(String saleid) {
		Bills.saleid = saleid;
	}
	public static int getAddressID() {
		return addressID;
	}
	public static void setAddressID(int addressID) {
		Bills.addressID = addressID;
	}
	public static java.util.Date getTimeStamp() {
		return timeStamp;
	}
	public static void setTimeStamp(java.util.Date timeStamp2) {
		Bills.timeStamp = timeStamp2;
	}
	public static double getTotalAmount() {
		return totalAmount;
	}
	public static void setTotalAmount(double totalAmount) {
		Bills.totalAmount = totalAmount;
	}
	public static String getDeliveryStatus() {
		return DeliveryStatus;
	}
	public static void setDeliveryStatus(String deliveryStatus) {
		DeliveryStatus = deliveryStatus;
	}
	
	static double calculateTotal(String saleid){
		double total=0;
		try {
			rs=st.executeQuery("select pid from orderdetails where saleid='"+saleid+"';");
			while(rs.next()){
				String pid=rs.getString("pid");
				rs1=stmt.executeQuery("select price from product where pid='"+pid+"';");
				if(rs1.next()){
					double amt=rs1.getDouble("price");
					total=total+amt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	@SuppressWarnings("static-access")
	static void insertBills(OrderDetails Od){
		saleid=Od.getSaleid();
		totalAmount = calculateTotal(saleid);
		try {
			prep = con.prepareStatement("insert into bills values(?,?,?,?,?);");
			prep.setDate(1,(Date) timeStamp);
			prep.setInt(2, addressID);
			prep.setString(3,saleid);
			prep.setDouble(4, totalAmount);
			prep.setString(5, DeliveryStatus);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
