package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Report;

@Mapper
public interface ReportRepository {


	List<Report> getListByPage(Integer offset, Integer page, Integer size, String query);

	int count();

	Report findUserReport(Long userReportId);

	Report findReport(Long reportId);

	Report findPostReport(Long reportId);

	Report findCommentReport(Long reportId);

	Integer reportProcessMain(Report reportSave);

	Integer reportProcessUser(Report reportSave);

	Integer reportProcessPost(Report reportSave);

	Integer reportProcessComment(Report reportSave);

}
