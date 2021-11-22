package com.data.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.*;

import com.data.DbUtil;
import com.model.Cart;
import com.model.Item;
import com.model.LineItem;
import com.model.Order;
import com.model.User;

public class OrderDAO {
	public OrderDAO() {}
	public Order CreateOrderForUser(Order order, User user) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			
			order.setUser(user);
			session.save(order);
			transaction.commit();
			return order;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
}
