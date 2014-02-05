package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Category
{
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;	
	public Category() throws SQLException {
		// TODO Auto-generated constructor stub
		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();
		st1=con.createStatement();
	}

	
	private static int categoryID=0;
	private static String categoryName;
	public int getCategoryID() {
		return categoryID;
	}
	@SuppressWarnings("static-access")
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	@SuppressWarnings("static-access")
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public int generateCategoryID(String CategoryName){
		try {
			rs=st.executeQuery("SELECT categoryName FROM category;");
			while(rs.next())
			{
				String name=rs.getString("categoryName");
				System.out.println("cat name : "+name);
				if(CategoryName.equals(name)){
					rs1=st1.executeQuery("SELECT categoryID FROM category where categoryName='"+CategoryName+"';");
					System.out.println("category name exists");
					if(rs1.next())
						categoryID=rs1.getInt("categoryID");
					return categoryID;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 2; i++) {
			categoryID=categoryID+(int)CategoryName.charAt(i);
			
		}
		insert();
		return categoryID;
	}
	
	
	public static void  insert()
	{
		try {
			prep = con.prepareStatement("insert into Category VALUES(?,?);");
			prep.setInt(1,categoryID);
			prep.setString(2,categoryName);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}