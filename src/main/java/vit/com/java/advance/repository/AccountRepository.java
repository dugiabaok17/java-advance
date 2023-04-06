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
import vit.com.java.advance.entity.Account;
import vit.com.java.advance.entity.Department;

public class AccountRepository {

	public List<Account> findAll() {

		Session session = HibernateConfig.getSessionFactory().openSession();

		List<Account> listAccount = session.createQuery("SELECT a FROM Account a", Account.class).getResultList();
		session.close();
		return listAccount;

	}

	public void insert(Account a) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.merge(a);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}
	}

	public boolean checkExistEmail(String email) {
		Transaction transaction = null;
		Session session = HibernateConfig.getSessionFactory().openSession();

		String hql = "FROM Account E WHERE E.email = :email";
		Query query = session.createQuery(hql, Department.class);
		query.setParameter("email", email);
		if (query.getResultList().size() < 1) {
			return false;
		}

		return true;
	}

	public boolean checkExistUserName(String userName) {
		Transaction transaction = null;
		Session session = HibernateConfig.getSessionFactory().openSession();

		String hql = "FROM Account E WHERE E.userName = :userName";
		Query query = session.createQuery(hql, Department.class);
		query.setParameter("userName", userName);
		if (query.getResultList().size() < 1) {
			return false;
		}

		return true;
	}

	public void update(Account a) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.merge(a);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}
	}

	public Account checkExistAccount(Integer id) {
		Transaction transaction = null;
		Session session = HibernateConfig.getSessionFactory().openSession();

		Account d = session.get(Account.class, id);
		if (d != null) {
			return d;
		}
		;

		return null;
	}

	public void delete(Integer id) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			Account d = session.get(Account.class, id);
			if (d != null) {
				session.remove(d);
				System.out.println("account 1 is deleted");
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
	
	public static List<Account> findAllCriteria () {
		Session session = HibernateConfig.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Account> cr = cb.createQuery(Account.class);
		Root<Account> root = cr.from(Account.class);
		cr.select(root);

		TypedQuery<Account> query = session.createQuery(cr);
		List<Account> results = query.getResultList();
		return results;
	}
	
	public static List<Account> findAllCriteria (String userName) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Account> cr = cb.createQuery(Account.class);
		Root<Account> root = cr.from(Account.class);
		cr.select(root).where(root.get("userName").in(userName));

		TypedQuery<Account> query = session.createQuery(cr);
		List<Account> results = query.getResultList();
		return results;
	}
	
	public static void main(String[] args) {
		AccountRepository.findAllCriteria("duvanan2002").forEach(System.out::println);
	}
}
