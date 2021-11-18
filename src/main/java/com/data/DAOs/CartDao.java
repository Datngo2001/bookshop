package com.data.DAOs;

import org.hibernate.*;

import com.data.DbUtil;
import com.model.Cart;
import com.model.LineItem;
import com.model.Product;
import com.model.User;

public class CartDAO {

	public CartDAO() {

	}

	public Cart getCart(int cartId) {
		Transaction transaction = null;
		Cart cart = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			cart = session.get(Cart.class, cartId);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cart;
	}

	public Cart getUserCart(int userId) {
		Transaction transaction = null;
		Cart cart = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = session.get(User.class, userId);
			cart = user.getCart();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cart;
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

	public LineItem addToCart(int cartId, int productId, int quantity) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Cart cart = new Cart();
			cart.setId(cartId);
			Product product = session.get(Product.class, productId);
			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			lineItem.setQuantity(quantity);
			lineItem.setCart(cart);
			session.save(lineItem);

			transaction.commit();
			return lineItem;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	// UPDATE ----------------------------------------------------

	public LineItem updateQuantity(int itemId, int quantity) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			LineItem lineItem = session.get(LineItem.class, itemId);
			lineItem.setQuantity(quantity);
			session.update(lineItem);

			transaction.commit();
			return lineItem;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	// DELETE ----------------------------------------------------

	public void RemoveItem(int itemId) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			LineItem lineItem = new LineItem();
			lineItem.setId(itemId);
			session.delete(lineItem);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Cart clearCart(int cartId) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Cart cart = session.get(Cart.class, cartId);
			cart.getItems().clear();
			session.update(cart);

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
}
