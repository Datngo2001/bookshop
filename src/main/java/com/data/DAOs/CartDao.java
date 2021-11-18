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

	public LineItem addToCart(Cart cart, int productId, int quantity) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Product product = session.get(Product.class, productId);
			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			lineItem.setQuantity(quantity);
			lineItem.setCart(cart);
			session.save(lineItem);
			cart.getItems().add(lineItem);
			cart.UpdateTotalPrice();

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

	public Cart updateQuantity(Cart cart, int itemId, int quantity) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			LineItem lineItem = session.get(LineItem.class, itemId);
			lineItem.setQuantity(quantity);
			session.update(lineItem);
			for (LineItem item : cart.getItems()) {
				if (item.getId() == itemId) {
					item.setQuantity(quantity);
				}
			}
			cart.UpdateTotalPrice();

			transaction.commit();
			return cart;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return cart;
		}
	}

	public Cart RemoveItem(Cart cart, int itemId) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			LineItem lineItem = new LineItem();
			lineItem.setId(itemId);
			session.delete(lineItem);
			cart.getItems().remove(lineItem);
			cart.UpdateTotalPrice();

			transaction.commit();
			return cart;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return cart;
		}
	}
}
