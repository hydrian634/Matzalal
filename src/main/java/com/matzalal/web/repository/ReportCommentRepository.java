package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.ReportComment;

@Mapper
public interface ReportCommentRepository {

	List<ReportComment> findAll();

	void save(ReportComment reportComment);

	ReportComment last();

}
