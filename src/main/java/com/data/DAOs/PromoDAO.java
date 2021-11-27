package com.data.DAOs;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.DbUtil;

import com.model.Promo;

public class PromoDAO {
	public PromoDAO() {
	}
	public void addPromoCode(Promo promo) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(promo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
