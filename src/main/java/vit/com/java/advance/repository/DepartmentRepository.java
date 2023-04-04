package vit.com.java.advance.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import vit.com.java.advance.config.HibernateConfig;
import vit.com.java.advance.entity.Department;

public class DepartmentRepository {

	public List<Department> findAll() {

		Session session = HibernateConfig.getSessionFactory().openSession();

		List<Department> listDepartments = session.createQuery("SELECT a FROM Department a", Department.class)
				.getResultList();
		session.close();
		return listDepartments;

	}

	public void insertOrUpdate(Department d) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.merge(d);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}
	}

	public void delete(Long id) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			Department d = session.get(Department.class, id);
			if (d != null) {
				session.remove(d);
				System.out.println("student 1 is deleted");
			}

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}
	}

}
