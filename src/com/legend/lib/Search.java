package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Search {
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	static Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;
	public ArrayList<Product> search(String key)
	{
		key=key.toLowerCase();
		ArrayList<Product> array=new ArrayList<Product>();
		db=new DBConnection();
		con=db.getConnection();
		try {
			st = con.createStatement();
			st1=con.createStatement();

//****************** Searching Products for key******************//		

			rs=st.executeQuery("select * from product;");
			while(rs.next())
			{
				String pname=rs.getString("name");
				String pid=rs.getString("pid");
				pname=pname.toLowerCase();
				pid=pid.toLowerCase();
				if(pname.substring(0, 3).equals(key.substring(0,3)))
				{
					Product p=new Product();
					p.setCategoryID(rs.getInt("categoryid"));
					p.setDescription(rs.getString("description"));
					p.setDiscount(rs.getDouble("discount"));
					p.setImagesrc(rs.getString("image"));
					p.setManufactureID(rs.getInt("manufactureid"));
					p.setPID(rs.getString("pid"));
					p.setPname(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					p.setQuantity(rs.getInt("quantity"));
					p.setRating(rs.getInt("rating"));
					if(!array.contains(p))
						array.add(p);
				}
				else if(pid.substring(0, 3).equals(key.substring(0,3)))
				{
					Product p=new Product();
					p.setCategoryID(rs.getInt("categoryid"));
					p.setDescription(rs.getString("description"));
					p.setDiscount(rs.getDouble("discount"));
					p.setImagesrc(rs.getString("image"));
					p.setManufactureID(rs.getInt("manufactureid"));
					p.setPID(rs.getString("pid"));
					p.setPname(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					p.setQuantity(rs.getInt("quantity"));
					p.setRating(rs.getInt("rating"));
					if(!array.contains(p))
						array.add(p);
				}
			}

	//*************** Searching categories for key******************//

			st=con.createStatement();
			rs=st.executeQuery("select * from category;");
			while(rs.next())
			{
				String cname=rs.getString("categoryname");
				int cid=rs.getInt("categoryid");
				cname=cname.toLowerCase();
				if(cname.substring(0,2).equals(key.substring(0,2)))
				{
					ArrayList<Product> array1=new ArrayList<Product>();
					helpFunctions help=new helpFunctions();
					array1=help.getProductsByCategory(cid);
					for (int i = 0; i < array1.size(); i++) {
						Product p=new Product();
						p=array1.get(i);
						if(!array.contains(p)){
							array.add(p);
						}
					}
				}
			}
			
	//**** Searching Manufacturers for key******************//
			
			st=con.createStatement();
			rs=st.executeQuery("select * from manufacturer;");
			while(rs.next())
			{
				String cname=rs.getString("manufacturename");
				int cid=rs.getInt("manufactureid");
				cname=cname.toLowerCase();
				if(cname.substring(0,2).equals(key.substring(0,2)))
				{
					st1=con.createStatement();
					rs1=st1.executeQuery("select * from product where manufactureid='"+cid+"';");
					while(rs1.next()){
						Product p=new Product();
						p.setCategoryID(rs1.getInt("categoryid"));
						p.setDescription(rs1.getString("description"));
						p.setDiscount(rs1.getDouble("discount"));
						p.setImagesrc(rs1.getString("image"));
						p.setManufactureID(rs1.getInt("manufactureid"));
						p.setPID(rs1.getString("pid"));
						p.setPname(rs1.getString("name"));
						p.setPrice(rs1.getDouble("price"));
						p.setQuantity(rs1.getInt("quantity"));
						p.setRating(rs1.getInt("rating"));
						if(!array.contains(p))
							array.add(p);
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}
}
