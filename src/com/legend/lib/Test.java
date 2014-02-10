package com.legend.lib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class Test  {
	public static void main(String[] args) throws Exception {
		Test t=new Test();
		t.ProductTest();
	}
	@SuppressWarnings("static-access")
	void ProductTest() throws Exception{
	
			Product p;
			
			helpFunctions help=new helpFunctions();
			ArrayList<Product> array=new ArrayList<Product>();
			array=help.getNewProducts();
			if(help.CheckProduct("asus-zen")){
				System.out.println(help.existingPID("asus-zen"));
			}
			else{
				
			}
			//help.crudProduct("Nikhitha","legend/images/bag.jpg","500","Bag","54","20.2","Nikhitha","Nikhitha");
	}
}
