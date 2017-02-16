package com.mioms.others.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mioms.core.entity.BaseEntity;





@Entity
@Table(name = "mio_question")
public class Question extends BaseEntity  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4529265317539977454L;

	private String title;
	
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
