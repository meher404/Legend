package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class helpFunctions {

	static DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static PreparedStatement prep;	

	public helpFunctions(){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean Login(String email,String pwd){
		String encryptedPwd,status = "";
		CaesarCipher cd=new CaesarCipher();
		encryptedPwd=cd.encrypt(pwd,2);
		try {
			rs=st.executeQuery("select email,password,userid from user;");
			while(rs.next()){
				String mail=rs.getString("email");
				String passwrd=rs.getString("password");
				String uid=rs.getString("userid");
				rs1=stmt.executeQuery("select status from registration where userid='"+uid+"';");
				if(rs1.next()){
					status=rs1.getString("status");
				}
				if(mail.equals(email) && encryptedPwd.equals(passwrd)&& status.equals("active")){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean CheckProductQuantity(String pid,int number){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			rs=st.executeQuery("select quantity from product where pid='"+pid+"';");
			if(rs.next()){
				int quan=rs.getInt("quantity");
				if(quan>=number){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateProductQuantity(String pid,int number){
		try{
			int quan = 0,value;
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			rs=st.executeQuery("select quantity from product where pid='"+pid+"';");
			if(rs.next()){
				 quan=rs.getInt("quantity");
			}
			value=quan-number;
			st.executeUpdate("update product set quantity='"+value+"'where pid='"+pid+"';");
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean checkStatus(String email){
		String status="";
		try {
			st=con.createStatement();
			rs=st.executeQuery("select userid from user where email='"+email+"';");
			while(rs.next()){
				String uid=rs.getString("userid");
				stmt=con.createStatement();
				rs1=stmt.executeQuery("select status from registration where userid='"+uid+"';");
				if(rs1.next()){
					status=rs1.getString("status");
				}
				if(status.equals("active")){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static boolean checkExistingEmail(String email) throws SQLException{
		st=con.createStatement();
		rs=st.executeQuery("select email from user");
		while(rs.next()){
			String mail=rs.getString("email");
			if(mail.equals(email)){
				return true;
			}
		}
		return false;
	}
	
	public static String generateSaleid(){
		String temp="sale00";
		int tem=temp.length()%10;
		temp=temp+tem+"";
		return temp;
	}
}
