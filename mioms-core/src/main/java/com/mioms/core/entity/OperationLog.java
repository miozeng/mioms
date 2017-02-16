package com.mioms.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "mio_operation_log")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="myCache") 
public class OperationLog   extends BaseEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -783181666875793937L;

//	private Long id;
	
	private User user;
	
	private Date operatiomTime;
	
	private String msg;


//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO) 
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP) 
	public Date getOperatiomTime() {
		return operatiomTime;
	}

	public void setOperatiomTime(Date operatiomTime) {
		this.operatiomTime = operatiomTime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	

}
