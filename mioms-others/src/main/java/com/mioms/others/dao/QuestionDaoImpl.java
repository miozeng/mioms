package com.mioms.others.dao;

import org.springframework.stereotype.Repository;

import com.mioms.core.dao.BaseDaoImpl;
import com.mioms.others.entity.Question;

@Repository
public class QuestionDaoImpl  extends BaseDaoImpl<Question, Long> implements QuestionDao{

}
