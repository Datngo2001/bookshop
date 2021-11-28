package com.data.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.taglibs.standard.lang.jstl.test.Bean1;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.DbUtil;

import com.model.Promo;
import com.model.User;

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
	public Promo getPromoCode(String code) {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		String sql = "Select p from Promo p where p.code =:pcode";
		TypedQuery<Promo> q = em.createQuery(sql, Promo.class);
		q.setParameter("pcode", code);
		try {
			Promo promo = q.getSingleResult();
			
			return promo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
	
}
