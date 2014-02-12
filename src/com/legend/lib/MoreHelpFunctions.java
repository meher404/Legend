package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MoreHelpFunctions {
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;
	
	public int checkAddressId(String doorno,String street,String city)
	{
		int addId=0;
		db=new DBConnection();
		con=db.getConnection();
		try {
			st = con.createStatement();
			rs=st.executeQuery("select addressid from address where doorNo='"+doorno+"' and street='"+street+"' and city='"+city+"';");
			if(rs.next()){
				addId=rs.getInt("addressid");
				return addId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return addId;
	}
	
	public void updateShipping(Bills b)
	{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st=con.createStatement();
			String saleid=b.getSaleid();
			String sql="update shipping set saleid='"+saleid+"';";
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double calculateAmount(User user){
		double amount=0;
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			HashMap<Product, Integer> cart=new HashMap<Product, Integer>();
		
			cart=user.getCart();
			
			for ( Product p : cart.keySet() ) {
				int quantity=cart.get(p);
				String pid=p.getPID();
				rs=st.executeQuery("select price from product where pid='"+pid+"';");
				if(rs.next()){
					double price=rs.getDouble("price");
					amount=amount+(price*quantity);
				}
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return amount;
	}
	
	
}
