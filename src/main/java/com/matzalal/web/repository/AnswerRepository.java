package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Answer;

@Mapper
public interface AnswerRepository {

	List<Answer> findAll();

	void save(Answer answer);

	Answer last();

	void modify(Answer answer);




}
