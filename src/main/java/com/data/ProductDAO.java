package com.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;

public class ProductDAO {
	
	String getProductQuery = "select * from product";
	
	public Database db;
	
	public ProductDAO() {
		db = new Database();
	}
	
	public List<Product> getProducts() throws Exception {
		List<Product> products = new ArrayList<>();
		Connection myCon = null;
		Statement mySta = null;
		ResultSet myRes = null;
		try {
			myCon = db.getConnection();
			mySta = myCon.createStatement();
			
			myRes = mySta.executeQuery(getProductQuery);
			while(myRes.next()) {
				int id = myRes.getInt("id");
				String name = myRes.getString("name");
				String des = myRes.getString("description");
				int price = myRes.getInt("price");
				double discount = price * 0.08;
				Product tempProduct = new Product(id, name, des, price, discount);
				products.add(tempProduct);
			}
			return products;
		}
		finally {
			db.closeConnection(myCon, mySta, myRes);
		}
		
	}

	public void addProduct(Product theProduct) throws ClassNotFoundException, SQLException {
		Connection myCon = null;
		PreparedStatement myPreS = null;
		
		try {
			myCon = db.getConnection();
			String insertProductQuery = "insert into product (name, description, price) values (?, ?, ?)";
			myPreS = myCon.prepareStatement(insertProductQuery);
			myPreS.setString(1, theProduct.getName());
			myPreS.setString(2, theProduct.getDescription());
			myPreS.setInt(3, theProduct.getPrice());
			myPreS.execute();
		}
		finally {
			db.closeConnection(myCon, myPreS, null);
		}
	}

	public Product getProducts(String theProductId) throws Exception {
		Product theProduct = null;
		Connection myCon = null;
		PreparedStatement myPres = null;
		ResultSet myRes = null;
		int productId;
		try {
			productId = Integer.parseInt(theProductId);
			myCon = db.getConnection();
			String sql = "select * from product where id=?";
			myPres = myCon.prepareStatement(sql);
			myPres.setInt(1, productId);
			myRes = myPres.executeQuery();
			if(myRes.next()) {
				String name = myRes.getString("name");
				String des = myRes.getString("description");
				int price = myRes.getInt("price");
				//double discount = price * 0.08;
				theProduct = new Product(name, des, price); 
			}
			else {
				throw new Exception("Could not fount the product id: " + productId);
			}
			return theProduct;
		}
		finally {
			db.closeConnection(myCon, myPres, myRes);
		}
		
	}

	public void updateProduct(Product theProduct) {
		
		
	}
	
	
}

