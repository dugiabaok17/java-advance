package vit.com.java.advance.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import vit.com.java.advance.entity.Account;
import vit.com.java.advance.entity.Address;
import vit.com.java.advance.entity.Department;
import vit.com.java.advance.entity.User;

public class HibernateConfig {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/java_advance?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "03376108862k2An");
				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

//				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(Department.class);
				configuration.addAnnotatedClass(Account.class);
				configuration.addAnnotatedClass(Address.class);
				configuration.addAnnotatedClass(User.class);
		

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static void main(String[] args) {
		getSessionFactory();
	}
}
