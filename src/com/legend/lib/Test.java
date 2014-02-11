package com.legend.lib;

import java.util.ArrayList;

public class Test  {
	public static void main(String[] args) throws Exception {
		Test t=new Test();
		t.ProductTest();
	}
	void ProductTest() throws Exception{
	
			deleteProduct delete=new deleteProduct();
			delete.deleteProductAdmin("nik");
			delete.deleteFromCart("binn9","book002");
			//help.crudProduct("Nikhitha","legend/images/bag.jpg","500","Bag","54","20.2","Nikhitha","Nikhitha");
	}
}
