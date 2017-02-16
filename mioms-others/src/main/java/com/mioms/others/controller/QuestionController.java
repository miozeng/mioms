package com.mioms.others.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mioms.others.dto.QuestionDto;
import com.mioms.others.service.QuestionService;



@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "question/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(final QuestionDto dto) {
		Map<String, Object> rep = new HashMap<String, Object>();
		questionService.save(dto);
		rep.put("ret", true);
		rep.put("msg", "save succeed");
		return rep;
	}
	
	@RequestMapping(value = "question/findAll", method = RequestMethod.GET)
	@ResponseBody
	public List<QuestionDto> findAll() {
		return questionService.findAll();
	}
}
