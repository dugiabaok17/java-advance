package vit.com.java.advance.service;

import java.util.List;

import vit.com.java.advance.entity.Account;
import vit.com.java.advance.entity.Department;

public interface IAccountService {
	void delete(Integer id);
	void insert(Account account);
	void update(Integer id,Account account);
	List<Account> fillAll();
}
