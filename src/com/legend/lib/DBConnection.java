package com.legend.lib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class DBConnection {
	private Connection con;
	DBConnection(){
	con= null;
	}
	public Connection getConnection(){
		try {
			Properties pro=new Properties();			
			FileInputStream fi = null;
			try {
				fi = new FileInputStream("F:\\WebProgramming\\WorkSpace\\Prac\\WebContent\\jdbc.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pro.load(fi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String driver=pro.getProperty("classname");
		//	System.out.println(driver);
			if(!driver.equals(null))
			{
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String url=pro.getProperty("path");
			//System.out.println(url);
			String user=pro.getProperty("username");
			//System.out.println(user);
			String password=pro.getProperty("password");
			//System.out.println(password);
			con = DriverManager.getConnection(url,user,password);
		//	System.out.println("Connection Established....");
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return con;
	}	
	public static void main(String[] args){
		DBConnection db=new DBConnection();
		db.getConnection();
	}
	
}
		
		