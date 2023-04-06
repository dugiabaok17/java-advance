package vit.com.java.advance.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
				System.out.println("department 1 is deleted");
			} else {
				System.out.println("Không tìm thấy department");
			}

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}
	}

	public boolean checkExistDepartmentName(Long id) {
		Transaction transaction = null;
		Session session = HibernateConfig.getSessionFactory().openSession();

		Department d = session.get(Department.class, id);
		if (d != null) {
			return false;
		}
		;

		return true;
	}

	public static List<Department> findAllCriteriaLike() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Department> cr = cb.createQuery(Department.class);
		Root<Department> root = cr.from(Department.class);
		cr.select(root).where(cb.like(root.get("name"), "s%"));
		;

		TypedQuery<Department> query = session.createQuery(cr);
		List<Department> results = query.getResultList();
		return results;
	}

	public static List<Department> findAllCriteria() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Department> cr = cb.createQuery(Department.class);
		Root<Department> root = cr.from(Department.class);
		cr.select(root);

		TypedQuery<Department> query = session.createQuery(cr);
		List<Department> results = query.getResultList();
		return results;
	}

	public static List<Department> getAllDepartmentWithPaging(int page, int size) {

		Session session = HibernateConfig.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Department",Department.class);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		return query.getResultList();

	}

	public static void main(String[] args) {
//		DepartmentRepository.findAllCriteria().forEach(System.out::println);
//		DepartmentRepository.findAllCriteriaLike().forEach(System.out::println);
		getAllDepartmentWithPaging(1, 5).forEach(data -> System.out.println(data));
		getAllDepartmentWithPaging(2, 5).forEach(data -> System.out.println(data));
	}

}
