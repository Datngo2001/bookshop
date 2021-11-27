package com.data.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.*;
import com.data.DbUtil;
import com.model.Product;

public class ProductDAO {
	public ProductDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From Product").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts(int index) throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From Product").setFirstResult(index)
					.setMaxResults(15).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void addProducts(Product product) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateProducts(Product product) {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			Product theProduct = em.find(Product.class, product.getId());
			theProduct.setNameAuthor(product.getNameAuthor());
			theProduct.setNXB(product.getNXB());
			theProduct.setProductName(product.getProductName());
			theProduct.setDescription(product.getDescription());
			theProduct.setPrice(product.getPrice());
			theProduct.setSupplier(product.getSupplier());
			em.merge(theProduct);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void deleteProduct(int id) {
		Transaction transaction = null;
		Product product = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
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
	}

	public Product getProduct(int id) {
		Transaction transaction = null;
		Product product = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			product = session.get(Product.class, id);
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

	public List<Product> getByName(String keyword) {
		String hql = "Select p from Product p where p.name like :keyword";
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		TypedQuery<Product> query = em.createQuery(hql, Product.class);

		try {
			query.setParameter("keyword", "%" + keyword + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
}
