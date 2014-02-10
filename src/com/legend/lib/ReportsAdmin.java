package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportsAdmin {
	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static PreparedStatement prep;	

	public String ReportsByProduct(){
		String str="-----------------------------------------------------"+"\n";
		str=str+"Product"+"\t"+"PID"+"\t"+"Total Items"+"\t"+"Number Of Items Sold"+"\t"+"Stock"+"\t"+"Revenue"+"\n";
		str=str+"-----------------------------------------------------"+"\n";
		int i=0;
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();

			rs1=stmt.executeQuery("select pid,name,price,quantity from product;");
			while(rs1.next()){
				String pid=rs.getString("pid");
				double price=rs.getDouble("price");
				String name=rs.getString("name");
				int quantity=rs.getInt("quantity");
				rs=st.executeQuery("select sum(quantity) from orderdetails where pid='"+pid+"';");
				while(rs.next()){
					int quan=rs.getInt(1);
					int available=quantity-quan;
					str=str+"name"+"\t"+"PID"+"\t"+"Total Items"+"\t"+"Number Of Items Sold"+"\t"+"Stock"+"\t"+"Revenue"+"\n";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
}
