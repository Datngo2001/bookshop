package com.data;

import java.net.URI;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.model.*;

public class DbUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/book_store?useSSL=false");
				settings.put(Environment.USER, "root");

				settings.put(Environment.PASS, "ngocthien2306.com"); // remember to chang to your password
				// {password,ngocthien2306.com}

				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

				settings.put("hibernate.connection.CharSet", "utf-8");
				settings.put("hibernate.connection.useUnicode", true);
				settings.put("hibernate.connection.characterEncoding", "utf-8");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				// settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.HBM2DDL_AUTO, "create-drop");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Category.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(Item.class);
				configuration.addAnnotatedClass(Cart.class);
				configuration.addAnnotatedClass(LineItem.class);
				configuration.addAnnotatedClass(Review.class);
				configuration.addAnnotatedClass(Photo.class);
				configuration.addAnnotatedClass(File.class);
				configuration.addAnnotatedClass(Promo.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactorys() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();

				String DATABASE_URL = System.getenv("DATABASE_URL");

				if (DATABASE_URL != null) {
					URI dbUri = new URI(DATABASE_URL);
					String username = dbUri.getUserInfo().split(":")[0];
					String password = dbUri.getUserInfo().split(":")[1];
					String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
					settings.put(Environment.URL, dbUrl);
					settings.put(Environment.USER, username);
					settings.put(Environment.PASS, password);
					settings.put(Environment.DRIVER, "org.postgresql.Driver");
					settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
				} else {
					settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
					settings.put(Environment.URL,
							"jdbc:mysql://localhost:3306/book_store?allowPublicKeyRetrieval=true&useSSL=false");
					settings.put(Environment.USER, "root");
					settings.put(Environment.PASS, "ngocthien2306.com");
					settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				}

				settings.put(Environment.SHOW_SQL, "true");

				// settings.put(Environment.HBM2DDL_AUTO, "create-drop");

				settings.put(Environment.HBM2DDL_AUTO, "update");
				// settings.put(Environment.HBM2DDL_AUTO, "create-drop");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Category.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(Item.class);
				configuration.addAnnotatedClass(Cart.class);
				configuration.addAnnotatedClass(LineItem.class);
				configuration.addAnnotatedClass(Review.class);

				configuration.addAnnotatedClass(Promo.class);
				configuration.addAnnotatedClass(File.class);
				configuration.addAnnotatedClass(Photo.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}