package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.repository.ReportReasonRepository;
import com.matzalal.web.repository.ReportRepository;

@Service
public class ReportServiceImp implements ReportService {
	
	@Autowired
	private ReportRepository repository;
	
	@Autowired
	private ReportReasonRepository reportReasonRepository;
	
	
	// 회원 신고목록조회(페이지별)
	@Override
	public List<Report> getListByPage(Integer offset, Integer page, Integer size, String query) {
		List<Report> list = repository.getListByPage(offset, page, size, query);
		return list;
	}

	// 신고 개수 조회
	@Override
	public int countReport() {
		return repository.count();
	}

	// 신고 사유 조회
	@Override
	public List<ReportReason> getReasonList() {
		
		return reportReasonRepository.findAll();
	}

	// 회원신고 조회
	@Override
	public Report getUserReport(Long userReportId) {
		Report userReport = repository.findUserReport(userReportId);
		return userReport;
	}

	// 신고내역 조회
	@Override
	public Report getReport(Long reportId) {
		Report report = repository.findReport(reportId);
		return report;
	}

	// 게시글 신고 조회
	@Override
	public Report getPostReport(Long reportId) {
		Report postReport = repository.findPostReport(reportId);
		return postReport;
	}

	// 댓글 신고 조회
	@Override
	public Report getCmtReport(Long reportId) {
		Report cmtReport = repository.findCommentReport(reportId);
		return cmtReport;
	}

	// 회원 활동 정지 기간 부여
	@Override
	public Integer reportProcessMain(Report reportSave) {
			System.out.println("회원 활동 정지기간 설정중");
			String result = reportSave.getSanctionTime();
			System.out.println("회원활동 정지기간" + result + "일");
		return repository.reportProcessMain(reportSave);
	}

	// 신고글 업데이트
	@Override
	public String reportProcessSub(Report reportSave) {
		 String gbn = reportSave.getReportGbn();
		 Integer result;
		 String rtnStr = "";
		 System.out.println("입력된 구분자 : "+gbn);
		 
		 if(gbn.equals("USER")) {
			result = repository.reportProcessUser(reportSave);
			System.out.println("회원 신고 내역 업데이트");			
		 } else if(gbn.equals("POST")) {
			 result = repository.reportProcessPost(reportSave);
			 System.out.println("게시글 신고 내역 업데이트");
		 } else {
			 result = repository.reportProcessComment(reportSave);
			 System.out.println("댓글 신고 내역 업데이트");
		 } 
		 
		 if(result == 0) {
			rtnStr = "신고처리 실패하였습니다."; 
		 } else {
			 rtnStr = "신고처리 성공하였습니다.";
		 }
		 
		return rtnStr;
		
	}



}
