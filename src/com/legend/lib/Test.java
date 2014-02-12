package com.legend.lib;

import java.util.ArrayList;

public class Test  {
	public static void main(String[] args) throws Exception {
		Test t=new Test();
		t.ProductTest();
	}
	void ProductTest() throws Exception{
	
		helpFunctions help=new helpFunctions();
		User user=new User();
		user=help.getFilledUser("binni@gmail.com");
		OrderDetails od=new OrderDetails();
		String str=od.OrderSummary(user,"sale002");
		System.out.println(str);
			//help.crudProduct("Nikhitha","legend/images/bag.jpg","500","Bag","54","20.2","Nikhitha","Nikhitha");
	}
}
