package com.mioms.others.dto;

import org.springframework.beans.BeanUtils;

import com.mioms.others.entity.Question;



public class QuestionDto {
	
	private String id;

	private String title;
	
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public static QuestionDto entityToDto(Question entity ){
		QuestionDto dto = new QuestionDto();
		BeanUtils.copyProperties(entity, dto);
		dto.setId(entity.getId().toString());
		return dto;
	}
	
	public static Question dtoToEntity(QuestionDto dto){
		Question entity = new Question();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

}
