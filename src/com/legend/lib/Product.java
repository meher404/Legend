package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Product {

	DBConnection db=null;
	static Connection con=null;
	Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	ResultSet rs=null;
	static PreparedStatement prep;	

	private static String pName;
	private static String PID;
	private static String imagesrc;
	private static double price;
	private static int quantity;
	private static String description;
	private static int manufactureID;
	private static int rating;
	private static double discount;
	private static int categoryID;
	public Product() throws SQLException{

		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();
	}

	public int getManufactureID() {
		return manufactureID;
	}

	@SuppressWarnings("static-access")
	public void setManufactureID(int manufactureID) {
		this.manufactureID = manufactureID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	@SuppressWarnings("static-access")
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getPname() {
		return pName;
	}
	@SuppressWarnings("static-access")
	public void setPname(String pName) {

		this.pName = pName;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getImagesrc() {
		return imagesrc;
	}
	@SuppressWarnings("static-access")
	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}
	public double getPrice() {
		return price;
	}
	@SuppressWarnings("static-access")
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	@SuppressWarnings("static-access")
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	@SuppressWarnings("static-access")
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	@SuppressWarnings("static-access")
	public void setRating(int rating) {
		this.rating = rating;
	}
	public double getDiscount() {
		return discount;
	}
	@SuppressWarnings("static-access")
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setCategory(Category c){
	}



	public Category getCategory(int categoryID) throws SQLException{
		Category c=new Category();
		String name=null;
		try {
			rs=st.executeQuery("select categoryName from Category where categoryID='"+categoryID+"';");
			if(rs.next())
			{
				name=rs.getString("categoryName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.setCategoryName(name);
		c.setCategoryID(categoryID);
		return c;
	}
	
	public Manufacturer getManufacturer(int ManufactureID) throws SQLException{
		Manufacturer m=new Manufacturer();
		String name=null,desc=null;
		try {
			rs=st.executeQuery("select manufactureName,description from manufacturer where ManufactureID='"+ManufactureID+"';");
			if(rs.next())
			{
				name=rs.getString("manufactureName");
				desc=rs.getString("description");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.setManfDescription(desc);
		m.setManufactureID(ManufactureID);
		m.setManufacturerName(name);
		return m;

	}

	String GeneratePID(int catID,int manfID){
		String id=catID+"";
		id=id+manfID+"";
		return id;

	}

	static void insertDB() throws SQLException
	{
		
		try {
			prep = con.prepareStatement("insert into product VALUES(?,?,?,?,?,?,?,?,?,?);");
			prep.setString(1,pName);
			prep.setString(2, PID);
			prep.setString(3,imagesrc); 
			prep.setDouble(4,price);
			prep.setInt(5,quantity); 
			prep.setString(6, description);
			prep.setInt(7,manufactureID); 
			prep.setInt(8, rating);
			prep.setDouble(9,discount); 
			prep.setInt(10,categoryID);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	static void deleteDB() throws SQLException
	{
		try {
			Statement st=conn.createStatement();
			String s="delete *from product where pid='"+PID+"';";
			st.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void updateDB(){

	}



}








