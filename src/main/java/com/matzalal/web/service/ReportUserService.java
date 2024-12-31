package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.entity.ReportUser;

public interface ReportUserService {
	
	List<ReportUser> getList();

	ReportUser add(ReportUser reportUser);

	List<ReportReason> getReasonList();

	// 회원 신고 목록 조회
	List<ReportUser> getListByPage(Integer offset, Integer page, Integer size, Long userReportId, String query);

	// 회원 신고글 개수
	int countReportUser();

	// 신고사유
	List<ReportReason> getReason();

	

}
