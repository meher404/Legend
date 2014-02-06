package com.legend.lib;

import java.sql.*;

public class Manufacturer
{
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;	
	public Manufacturer() throws SQLException {
		// TODO Auto-generated constructor stub
		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();
		st1 = con.createStatement();
	}



	private static String manufacturerName;
	private static String manfDescription;
	private static int manufactureID;
	public int getManufactureID() {
		return manufactureID;
	}
	@SuppressWarnings("static-access")
	public void setManufactureID(int manufactureID) {
		this.manufactureID = manufactureID;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	@SuppressWarnings("static-access")
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getManfDescription() {
		return manfDescription;
	}
	@SuppressWarnings("static-access")
	public void setManfDescription(String manfDescription) {
		this.manfDescription = manfDescription;
	}


	public int generatemanufactureID(String manufacturerName)
	{

		try {
			rs=st.executeQuery("SELECT manufactureName FROM manufacturer;");
			while(rs.next())
			{
				String name=rs.getString("manufactureName");
				System.out.println(name);
				if(manufacturerName.equals(name)){
					rs1=st1.executeQuery("SELECT manufactureID FROM manufacturer where manufactureName='"+manufacturerName+"';");
					if(rs1.next())
						manufactureID=rs1.getInt("manufactureID");
					return manufactureID;
			
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = manufacturerName.length()-1; i >manufacturerName.length()-3; i--) {
			manufactureID=manufactureID+(int)manufacturerName.charAt(i);
		}
		insert();
		return manufactureID;

	}

	public static void  insert()
	{
		try {
			
			prep = con.prepareStatement("insert into manufacturer VALUES(?,?,?);");
			prep.setInt(1,manufactureID);
			prep.setString(2, manufacturerName);
			prep.setString(3, manfDescription);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}