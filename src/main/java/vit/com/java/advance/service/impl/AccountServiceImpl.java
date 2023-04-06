package vit.com.java.advance.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import vit.com.java.advance.entity.Account;
import vit.com.java.advance.entity.Department;
import vit.com.java.advance.repository.AccountRepository;
import vit.com.java.advance.repository.DepartmentRepository;
import vit.com.java.advance.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

	private AccountRepository accountRepository;

	private DepartmentRepository departmentRepository;

	public AccountServiceImpl() {
		this.accountRepository = new AccountRepository();
		this.departmentRepository = new DepartmentRepository();
	}

	@Override
	public void delete(Integer id) {
		accountRepository.delete(id);
	}

	@Override
	public void insert(Account account) {
		if (accountRepository.checkExistEmail(account.getEmail())) {
			System.out.println("email đã tồi tại");
			return;
		} else if (accountRepository.checkExistUserName(account.getUserName())) {
			System.out.println("user name đã tồi tại");
			return;
		} else if (departmentRepository.checkExistDepartmentName(account.getDepartment().getId())) {
			System.out.println("department id  không tồn tại");
			return;
		}
		Account a = new Account();

		a.setEmail(account.getEmail());
		a.setFullName(account.getFullName());
		a.setUserName(account.getUserName());
		a.setCreateDate(LocalDateTime.now());
		a.setDepartment(account.getDepartment());
//	
		this.accountRepository.insert(a);
	}

	@Override
	public List<Account> fillAll() {

		return accountRepository.findAll();
	}

	@Override
	public void update(Integer id, Account account) {
		if (accountRepository.checkExistAccount(id) == null) {
			System.out.println("Account không tồn tại");
			return;
		} else if (departmentRepository.checkExistDepartmentName(account.getDepartment().getId())) {
			System.out.println("department id  không tồn tại");
			return;
		}
		Account a = accountRepository.checkExistAccount(id);
		a.setFullName(account.getFullName());
		a.setDepartment(account.getDepartment());
		this.accountRepository.update(a);;
		
	}

}
