package com.legend.lib;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class OrderDetails {
	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rs2=null;
	ResultSet rs3=null;
	static PreparedStatement prep,prep1;

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
	public String insertOrders(User user,int addressId) throws SQLException{
		String str="";
		try {
		
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			deleteProduct delete=new deleteProduct();

			HashMap<Product, Integer> cart=new HashMap<Product, Integer>();

			Bills bill=new Bills();

			cart=user.getCart();
			String uid=user.getUserID();
			System.out.println();
			String saleid=helpFunctions.generateSaleid();

			for ( Product p : cart.keySet() ) {

				int quantity=cart.get(p);
				String pid=p.getPID();
				helpFunctions help=new helpFunctions();
				boolean validate=help.CheckProductQuantity(pid, quantity);
				

				prep = con.prepareStatement("insert into orderdetails values(?,?,?,?);");
				prep.setString(1,saleid);
				prep.setString(2, uid);
				prep.setString(3,pid);
				prep.setInt(4, quantity);
				prep.executeUpdate();
				help.updateProductQuantity(pid, quantity);
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String str_date=dateFormat.format(date);
			double totalAmount=bill.calculateTotal(saleid);
			String deliveryStatus="pending";
			prep1=con.prepareStatement("insert into bills values(?,?,?,?,?);");
			prep1.setString(1,str_date);
			prep1.setInt(2,addressId);
			prep1.setString(3,saleid);
			prep1.setDouble(4,totalAmount);
			prep1.setString(5,deliveryStatus);
			prep1.executeUpdate();

			delete.deleteFromCart(uid, saleid);  //deleting from cart after successfull transaction

			str=OrderSummary(user,saleid);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}


	public String OrderSummary(User user,String saleid){
		String str="";
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
			
			String uid=user.getUserID();
			String str_date="",doorno="",street="",city="",state="",pid="",pname="";
			int pincode=0,addId=0,quantity=0;
			double total=0,price=0;
			String uname=user.getName();
			rs1=st.executeQuery("select * from bills where saleid='"+saleid+"';");
			while(rs1.next()){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = rs1.getTimestamp("timestamp");
				str_date=dateFormat.format(date);
				addId=rs1.getInt("addressid");
				total=rs1.getDouble("totalAmount");
			}
			st = con.createStatement();
			rs2=st.executeQuery("select * from address where addressid='"+addId+"';");
			while(rs2.next()){
				doorno=rs2.getString("doorno");
				street=rs2.getString("street");
				city=rs2.getString("city");
				state=rs2.getString("state");
				pincode=rs2.getInt("pincode");
			}
			String address=uname+","+doorno+","+street+","+city+","+state+","+pincode+"";
			

			str="<table>"+"<tr>"+"<td>"+"Sale ID:"+saleid+"</td>"+"<td>"+"Customer:"+uname+"</td>"+"<td>"+"Purchase Time:"+str_date+"</td>"+"</tr>"
					+"<tr>"+"<td>"+"Deliver to:"+address+"</td>"+"<td>"+"Customer:"+uname+"</td>"+"<td>"+""+"</td>"+"</tr>"
					+"<tr>"+"<td>"+"Product Name"+"</td>"+"<td>"+"Quantity"+"</td>"+"<td>"+"Price(Including shipping and Tax"+"</td>"+"</tr>";

			st = con.createStatement();
			rs=st.executeQuery("select * from orderdetails where saleid='"+saleid+"' and userid='"+uid+"';");
			while(rs.next()){
				pid=rs.getString("pid");
				quantity=rs.getInt("quantity");
				rs3=stmt.executeQuery("select name,price from product where pid='"+pid+"';");
				while(rs3.next()){
					price=rs3.getDouble("price");
					pname=rs3.getString("name");
				}
				str=str+"<tr>"+"<td>"+pname+"</td>"+"<td>"+quantity+"</td>"+"<td>"+price+"</td>"+"</tr>";

			}
			str=str+"</table>";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return str;
	}


}
