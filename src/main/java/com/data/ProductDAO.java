package com.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.ejb.*;
import org.jboss.jandex.TypeTarget.Usage;
import org.hibernate.*;
import com.model.Product;


public class ProductDAO {
	
	String getProductQuery = "select * from product";
	
	public Database db;
	
	public ProductDAO() {
		db = new Database();
	}
    @SuppressWarnings("unchecked")
	public List<Product> getProducts() throws Exception {
		try {
			return hibernateDb.getSessionFactory().openSession().createQuery("From Product").getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
    
	public void addProducts(Product product) {
		Transaction transaction = null;
		try (Session session = hibernateDb.getSessionFactory().openSession()) {
		
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void updateProducts(Product product) {
		Transaction transaction = null;
		try (Session session = hibernateDb.getSessionFactory().openSession()) {
		
			transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public Product deleteProduct(int id) {
		Transaction transaction = null;
		Product product = null;
        try (Session session = hibernateDb.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            product = session.get(Product.class, id);
            if (product != null) {
            	session.delete(product);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return product;
	}
	public Product getProduct(String id) {
		Transaction transaction = null;
		int ids = Integer.parseInt(id);
		Product product = null;
        try (Session session = hibernateDb.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            product = session.get(Product.class, ids);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return product;
	}
	public void addProduct(Product theProduct) throws ClassNotFoundException, SQLException {
		Connection myCon = null;
		PreparedStatement myPreS = null;
		
		try {
			myCon = db.getConnection();
			String insertProductQuery = "insert into product (name, description, price) values (?, ?, ?)";
			myPreS = myCon.prepareStatement(insertProductQuery);
			myPreS.setString(1, theProduct.getNameAuthor());
			myPreS.setString(2, theProduct.getDescription());
			myPreS.setInt(3, theProduct.getPrice());
			myPreS.execute();
		}
		finally {
			db.closeConnection(myCon, myPreS, null);
		}
	}
	/*
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
				String name = myRes.getString("nameAuthor");
				String des = myRes.getString("description");
				int price = myRes.getInt("price");
				//double discount = price * 0.08;
				theProduct = new Product(name, des, price, ""); 
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
	*/
	public void updateProduct(Product theProduct) {
		
		
	}

	
	
}

