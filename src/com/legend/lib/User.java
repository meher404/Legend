package com.legend.lib;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;


public class User {
	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static Statement stmt2 = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static ResultSet rs2=null;
	static PreparedStatement prep;	

	public User(){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt = con.createStatement();
			stmt2 = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static String name;
	private static String userID;
	private static String email;
	private static String pwd;
	private static long phne;
	private static int addressID;
	private static String gender;
	private static Date dob;

	public String getName() {
		return name;
	}

	@SuppressWarnings("static-access")
	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}

	@SuppressWarnings("static-access")
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	@SuppressWarnings("static-access")
	public void setEmail(String email) {
		this.email = email;
	}

	public static String getPwd() {
		return pwd;
	}

	@SuppressWarnings("static-access")
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public long getPhne() {
		return phne;
	}

	@SuppressWarnings("static-access")
	public void setPhne(long phne) {
		this.phne = phne;
	}

	public int getAddressID() {
		return addressID;
	}

	@SuppressWarnings("static-access")
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getGender() {
		return gender;
	}

	@SuppressWarnings("static-access")
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	@SuppressWarnings("static-access")
	public void setDob(Date dob) {
		this.dob = dob;
	}



	public String generateUserId(String name,int addId){
		String uid="";
		uid=name.substring(0,4)+addId%10;
		try {
			rs=st.executeQuery("select userID from user");
			while(rs.next()){
				String str=rs.getString("userID");
				if(str.equals("uid")){
					addId=addId%10;
					generateUserId(name,addId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uid;
	}

	@SuppressWarnings("static-access")
	public static void insertUser(){
		try {
			System.out.println(getPwd());
			String encpwd= encryptPwd(getPwd());
			prep = con.prepareStatement("insert into user VALUES(?,?,?,?,?,?,?,?);");
			prep.setString(1,name);
			prep.setString(2, userID);
			prep.setString(3,email); 
			prep.setString(4,encpwd);
			prep.setLong(5,phne); 
			prep.setInt(6, addressID);
			prep.setString(7,gender); 
			prep.setDate(8, dob);
			prep.executeUpdate();
			Registration reg=new Registration();
			reg.generateCode(userID, email);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	

	@SuppressWarnings("static-access")
	public static Cart getCart(){
		Cart c=new Cart();
		String proId = "";
		int quan=0;
		try {
			rs=st.executeQuery("select * from Cart where userID='"+userID+"';");
			if(rs.next())
			{
				proId=rs.getString("pid");
				quan=rs.getInt("quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.setPid(proId);
		c.setUid(userID);
		c.setQuantity(quan);
		return c;

	}

	public static void deleteUser(){
		try {
			Statement st=conn.createStatement();
			String s="delete *from user where pid='"+userID+"';";
			st.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public Address getAddress(int addressID) throws SQLException{
		Address a=new Address();
		String doorNo="",street="",city="",state="";
		long pin=0;
		try {
			rs=st.executeQuery("select * from address where addressID='"+addressID+"';");
			if(rs.next())
			{
				doorNo=rs.getString("doorNo");
				street=rs.getString("street");
				city=rs.getString("city");
				state=rs.getString("state");
				pin=rs.getLong("pincode");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		a.setDoorNo(doorNo);
		a.setStreet(street);
		a.setCity(city);
		a.setState(state);
		a.setPincode(pin);
		return a;
	}

	public static String encryptPwd(String pwd){
		String encryptedPwd;
		CaesarCipher cd=new CaesarCipher();
		encryptedPwd=cd.encrypt(pwd,2);
		return encryptedPwd;
	}

	@SuppressWarnings({ "rawtypes", "static-access" })
	public static ArrayList getOrders(String userid) throws SQLException{
		ArrayList<OrderDetails> od=new ArrayList<OrderDetails>();

		rs=st.executeQuery("select * from orderdetails where userid='"+userid+"';");
		int i=0;
		while(rs.next()){
			String saleid=rs.getString("saleid");
			String pid=rs.getString("pid");
			int quantity=rs.getInt("quantity");
			System.out.println("************************************************");
			System.out.println("from table");
			System.out.println(saleid+"\n"+pid+"\n"+quantity);
			OrderDetails order=new OrderDetails();
			order.setPid(pid);
			order.setQuantity(quantity);
			order.setSaleid(saleid);
			order.setUserId(userid);
			System.out.println("************************************************");
			System.out.println("from order after selecting and before adding to arraylist");
			System.out.println(order.getPid());
			System.out.println(order.getQuantity());
			System.out.println(order.getSaleid());
			System.out.println(order.getUserId());
			od.add(order);
			System.out.println("************** from array list************8");
			OrderDetails od1= new OrderDetails();
			//		System.out.println(od.get(i).toString());
			od1=od.get(i);
			System.out.println(od1.getPid());
			System.out.println(od1.getQuantity());
			System.out.println(od1.getSaleid());
			System.out.println(od1.getUserId());
			//System.out.println(od.toString());
		}
		return od;
	}

	@SuppressWarnings({ "static-access", "rawtypes" })
	public static ArrayList getBills(String userid) throws SQLException{
		ArrayList<Bills> bills=new ArrayList<Bills>();
		Vector<String> v=new Vector<String>();
		rs1=stmt.executeQuery("select saleid from orderdetails where userid='"+userid+"';");
		while(rs1.next()){
			String saleid=rs1.getString("saleid");
			if(!v.contains(saleid)){
				v.add(saleid);
			}
			else{
				continue;
			}
		//	System.out.println("hii"+saleid);
			rs2=stmt2.executeQuery("select * from bills where saleid='"+saleid+"';");
			if(rs2.next()){
			
				Bills bill=new Bills();
				java.util.Date timeStamp=rs2.getDate("timeStamp");
				int addId=rs2.getInt("addressID");
				String sid=rs2.getString("saleid");
				Double totalamt=rs2.getDouble("totalamount");
				String deliveryStatus=rs2.getString("DeliveryStatus");
				bill.setAddressID(addId);
				bill.setDeliveryStatus(deliveryStatus);
				bill.setSaleid(sid);
				bill.setTimeStamp(timeStamp);
				bill.setTotalAmount(totalamt);
				bills.add(bill);
			}
		}
		return bills;
	}

}
