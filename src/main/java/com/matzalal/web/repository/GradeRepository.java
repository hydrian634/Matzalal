package com.matzalal.web.repository;

import java.util.List;

import com.matzalal.web.entity.GradeView;
import com.matzalal.web.entity.UserGrade;
import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Grade;

@Mapper
public interface GradeRepository {

	public List<Grade> findAll();

	// email - grade (권한 찾기)
	List<GradeView> findViewAllById(String email);

	void save(UserGrade userGrade);
}
