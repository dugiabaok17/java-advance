package vit.com.java.advance;

import java.time.LocalDateTime;

import vit.com.java.advance.entity.Account;
import vit.com.java.advance.entity.Department;
import vit.com.java.advance.repository.DepartmentRepository;
import vit.com.java.advance.service.IAccountService;
import vit.com.java.advance.service.IDepartmentService;
import vit.com.java.advance.service.impl.AccountServiceImpl;
import vit.com.java.advance.service.impl.DepartmentServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		IDepartmentService s = new DepartmentServiceImpl();
//		s.insertOrUpdate(new Department(10L,"gia hoang ha"));
//		s.delete(3L);
		s.fillAll().forEach(System.out::println);
		
		
//		
		IAccountService a = new AccountServiceImpl();
//		a.fillAll().forEach(System.out::println);
//		public Account(String email, String userName, String fullName, LocalDateTime createDate, Department department) {
//		a.update(10,new Account("du thi thu ha",new Department(4L)));
//		a.delete(10);
	}
}
