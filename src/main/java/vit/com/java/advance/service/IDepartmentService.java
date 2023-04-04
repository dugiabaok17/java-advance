package vit.com.java.advance.service;

import java.util.List;

import vit.com.java.advance.entity.Department;

public interface IDepartmentService {
	void delete(Long id);
	void insertOrUpdate(Department department);
	List<Department> fillAll();
}
