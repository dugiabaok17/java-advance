package vit.com.java.advance.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private List<Account> listAccount;

	public Department() {

	}

	public Department(String name) {

		this.name = name;
	}

	public Department(Long id) {

		this.id = id;
	}

	public Department(Long id, String name) {

		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
//
//	@Override
//	public String toString() {
//		return "\nDepartment [id=" + id + ", name=" + name + ",\n List account =" + listAccount + "]";
//	}

	
}
