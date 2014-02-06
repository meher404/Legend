package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Address {
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;	

	public Address() throws SQLException {
		// TODO Auto-generated constructor stub
		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();
		st1=con.createStatement();
	}
	
	private static int addressID;
	private static String doorNo;
	private static String street;
	private static String city;
	private static String state;
	private static Long pincode;

	public static int getAddressID() {
		return addressID;
	}
	public static void setAddressID(int addressID) {
		Address.addressID = addressID;
	}
	public static String getDoorNo() {
		return doorNo;
	}
	public static void setDoorNo(String doorNo) {
		Address.doorNo = doorNo;
	}
	public static String getStreet() {
		return street;
	}
	public static void setStreet(String street) {
		Address.street = street;
	}
	public static String getCity() {
		return city;
	}
	public static void setCity(String city) {
		Address.city = city;
	}
	public static String getState() {
		return state;
	}
	public static void setState(String state) {
		Address.state = state;
	}
	public static Long getPincode() {
		return pincode;
	}
	public static void setPincode(Long pincode) {
		Address.pincode = pincode;
	}
	
	public int generateAddressId(long pin){
		int addid;
		addid=(int) (1000+(pin/10));
		try {
			rs=st.executeQuery("select addressID from address");
			while(rs.next()){
				int str=rs.getInt("addressID");
				if(str==addid){
					System.out.println(addid+"repeated");
					pin=pin/10;
					generateAddressId(pin);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addid;
	}

	public void insertAddress(){
		try {
			prep = con.prepareStatement("insert into address VALUES(?,?,?,?,?,?);");
			prep.setInt(1,addressID);
			prep.setString(2, doorNo);
			prep.setString(3,street); 
			prep.setString(4,city);
			prep.setString(5,state); 
			prep.setLong(6, pincode);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
}
