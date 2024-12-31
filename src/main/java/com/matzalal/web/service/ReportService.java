package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.ReportReason;

public interface ReportService {

	// 신고 목록 가져오기
	List<Report> getListByPage(Integer offset, Integer page, Integer size, String query);

	// 신고 목록 개수
	int countReport();

	// 신고사유 조회
	List<ReportReason> getReasonList();

	Report getUserReport(Long userReportId);

	Report getReport(Long reportId);

	Report getPostReport(Long reportId);

	Report getCmtReport(Long reportId);

	Integer reportProcessMain(Report reportSave);

	String reportProcessSub(Report reportSave);

	
}
