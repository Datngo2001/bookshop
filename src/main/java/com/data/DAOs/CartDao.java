package com.data.DAOs;

import java.util.List;



import org.hibernate.*;

import com.data.DbUtil;
import com.model.CardList;
import com.model.Product;

public class CartDao {
	
	public CartDao() {
		
	}
	@SuppressWarnings("unchecked")
	public List<CardList> getCartList(int id) {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From CardList C where C.uid = " + id).getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<CardList> getCartLists() {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From CardList").getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void addToCart(CardList cart) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(cart);
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public boolean checkNameExist(String name, int id) {
		List a = getCheck(name, id);
		
		if(a.size() == 1) 
			return true;
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<CardList> getCheck(String name, int id) {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From CardList C where C.uid = " + id + " and C.name =" + "'" +name + "'").getResultList();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public CardList removeProduct(int productId) {
		Transaction transaction = null;
		CardList cart = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			cart = session.get(CardList.class, productId);
			if (cart != null) {
				session.delete(cart);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cart;
		
	}
}
