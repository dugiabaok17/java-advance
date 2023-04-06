package vit.com.java.advance.service.impl;

import java.util.List;
import java.util.stream.Stream;

import vit.com.java.advance.entity.Department;
import vit.com.java.advance.repository.DepartmentRepository;
import vit.com.java.advance.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {

	private DepartmentRepository departmentRepository;

	public DepartmentServiceImpl() {
		this.departmentRepository = new DepartmentRepository();
	}

	@Override
	public void insertOrUpdate(Department department) {
		List<Department> list = departmentRepository.findAll().stream().filter(data -> data.getName().equalsIgnoreCase(department.getName())).toList();
		if(list.size() > 0) {
			System.out.println("Không để trùng tên phòng ban");
			return;
		}
		departmentRepository.insertOrUpdate(department);
	}

	@Override
	public void delete(Long id) {
		departmentRepository.delete(id);

	}

	@Override
	public List<Department> fillAll() {
		return departmentRepository.findAll();
	}

	

}
