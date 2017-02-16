package com.mioms.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//
@Entity
@Table(name = "mio_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="myCache") 
public class User  extends BaseEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5673518976404012588L;



	@Column(name="u_name")
	private String name;
	
	@Column(name="u_age")
	private String age;
	
	@Column(name="u_password")
	private String password;
	
	private Role role;
	
	private Set<OperationLog>  logs = new HashSet<OperationLog>();


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	public Set<OperationLog> getLogs() {
		return logs;
	}

	public void setLogs(Set<OperationLog> logs) {
		this.logs = logs;
	}

	
	
	
	
	

}
