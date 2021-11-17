package com.data.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.*;

import com.data.DbUtil;
import com.model.Cart;
import com.model.User;

public class CartDao {

	public CartDao() {

	}

	@SuppressWarnings("unchecked")
	public List<Cart> getCartList(String uname) {
		try {
			return DbUtil.getSessionFactory().openSession()
					.createQuery("From CardList C where C.username = " + "'" + uname + "'").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Cart> getCartLists() {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From CardList").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// INSERT ----------------------------------------------------
	public Cart CreateCart(Cart cart) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.save(cart);
			transaction.commit();
			return cart;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public Cart CreateCartForUser(Cart cart, User user) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			cart.setUser(user);
			session.save(cart);
			transaction.commit();
			return cart;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public void addToCart(Cart cart) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(cart);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public boolean checkNameExist(String name, String uname) {
		List a = getCheck(name, uname);

		if (a.size() == 1)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Cart> getCheck(String name, String uname) {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery(
					"From CardList C where C.username = " + "'" + uname + "'" + " and C.name =" + "'" + name + "'")
					.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cart removeProduct(int productId) {
		Transaction transaction = null;
		Cart cart = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			cart = session.get(Cart.class, productId);
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

	public void removeAllProduct(String name) {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "DELETE from CardList c where c.username =:uname";
		Query q = em.createQuery(sql);
		q.setParameter("uname", name);
		int count = 0;
		try {
			trans.begin();
			count = q.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
