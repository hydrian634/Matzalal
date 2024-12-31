package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.ReportPost;

@Mapper
public interface ReportPostRepository {

	List<ReportPost> findAll();

	void save(ReportPost reportPost);

	ReportPost last();

}
