package vit.com.java.advance;

import vit.com.java.advance.entity.Department;
import vit.com.java.advance.repository.DepartmentRepository;
import vit.com.java.advance.service.IDepartmentService;
import vit.com.java.advance.service.impl.DepartmentServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		IDepartmentService s = new DepartmentServiceImpl();
//		s.insertOrUpdate(new Department("an"));

		s.delete(8L);
		s.fillAll().forEach(System.out::println);
	}
}
