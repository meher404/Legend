package com.legend.lib;

import java.util.ArrayList;

public class Test  {
	public static void main(String[] args) throws Exception {
		Test t=new Test();
		t.ProductTest();
	}
	void ProductTest() throws Exception{
	
			ArrayList<Product> array=new ArrayList<Product>();
			Search s=new Search();
			array=s.search("galaxy");
			for (int i = 0; i < array.size(); i++) {
				Product pro=new Product();
				pro=array.get(i);
				System.out.println(pro.getPname());
			}
			//help.crudProduct("Nikhitha","legend/images/bag.jpg","500","Bag","54","20.2","Nikhitha","Nikhitha");
	}
}
