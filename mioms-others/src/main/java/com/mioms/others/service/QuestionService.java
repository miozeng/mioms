package com.mioms.others.service;

import java.util.List;

import com.mioms.others.dto.QuestionDto;




public interface QuestionService {
	
	public void save(QuestionDto dto);
	
	public List<QuestionDto> findAll();

}
