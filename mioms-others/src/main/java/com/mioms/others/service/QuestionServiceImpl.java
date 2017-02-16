package com.mioms.others.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mioms.others.dao.QuestionDao;
import com.mioms.others.dto.QuestionDto;
import com.mioms.others.entity.Question;



@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao  questionDao;

	public void save(QuestionDto dto) {
		
		Question question = QuestionDto.dtoToEntity(dto);
		questionDao.save(question);
	}

	public List<QuestionDto> findAll() {
		List<Question> questions = questionDao.findAll();
		List<QuestionDto> dtos = new ArrayList<QuestionDto>();
		for (Question question : questions) {
			QuestionDto dto = QuestionDto.entityToDto(question);
			dtos.add(dto);
		}
		return dtos;
	}
}
