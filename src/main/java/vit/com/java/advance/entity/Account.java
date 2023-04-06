package vit.com.java.advance.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;

	@Column(name = "user_name", unique = true, nullable = false, length = 50)
	private String userName;

	@Column(name = "full_name", length = 50, nullable = false)
	private String fullName;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	public Account() {

	}

	
	public Account(String fullName, Department department) {
		this.fullName = fullName;
		this.department = department;
	}


	public Account(String email, String userName, String fullName, Department department) {

		this.email = email;
		this.userName = userName;
		this.fullName = fullName;

		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", email=" + email + ", userName=" + userName + ", fullName="
				+ fullName + ", createDate=" + createDate + ", department=" + getDepartment().getName() + "]";
	}

}
