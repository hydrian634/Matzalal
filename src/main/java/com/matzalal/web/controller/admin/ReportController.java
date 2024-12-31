package com.matzalal.web.controller.admin;

import com.matzalal.web.entity.Report;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("adminReportController")
@RequestMapping("/admin/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	// 회원 신고 목록 조회
	@GetMapping("list")
	public String uReportList(
			@RequestParam(defaultValue = "1") int page, Model model		
	) {
		// 페이지 계산 변수
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		
		// 목록 조회
		List<Report> reportList = reportService.getListByPage(offset, page, size, null);
		
		
		// 개수 조회
		int count = reportService.countReport();
		int pageCount = count / size;
		
		// 모델에 담아서 전달
		model.addAttribute("reportList", reportList);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		System.out.println("리스트 중에 어디 눌렀닝?!"+ reportList);
		System.out.println("회원 신고 총 "+count+"건");
		
		return "/admin/report/list";
	}
	
	// 회원 신고 처리페이지 조회
	@GetMapping("userProcess")
	public String userProcess(
		@RequestParam Long reportId, Model model
	) {
		System.out.println("신고 처리 페이지 조회");
//		Report report = reportService.getReport(reportId);
//		System.out.println(report);
//		String result = report.getReportGbn();
		
		Report userReport = reportService.getUserReport(reportId);
		model.addAttribute("report", userReport);	
		System.out.println("회원신고 접수중");
		System.out.println(userReport);
		System.out.println("~~~~~~~~~~~~~~~~");
			
		// 신고 사유
		List<ReportReason> list = reportService.getReasonList();
		model.addAttribute("list", list);
		
		return "admin/report/process";
	}
	
	// 게시글 신고 페이지 조회
	@GetMapping("postProcess")
	public String postProcess(
		@RequestParam Long reportId, Model model
	) {
		
		System.out.println("게시글 신고 처리 페이지 조회");
		
		Report postReport = reportService.getPostReport(reportId);
		model.addAttribute("report", postReport);
		System.out.println("==================");
		System.out.println("게시글 신고 접수중");
		System.out.println(postReport);


		// 신고 사유
		List<ReportReason> list = reportService.getReasonList();
		model.addAttribute("list", list);
		
		return "admin/report/process";
	}
	
	// 댓글 신고 처리페이지 조회
		@GetMapping("commentProcess")
		public String commentProcess(
			@RequestParam Long reportId, Model model
		) {
			System.out.println("댓글 신고 처리 페이지 조회");
//			Report report = reportService.getReport(reportId);
//			System.out.println(report);
//			String result = report.getReportGbn();
			
			Report commentReport = reportService.getCmtReport(reportId);
			model.addAttribute("report", commentReport);
			System.out.println("==================");
			System.out.println("댓글 신고 접수중");
			System.out.println(commentReport);
			
			// 신고 사유
			List<ReportReason> list = reportService.getReasonList();
			model.addAttribute("list", list);
			
			return "admin/report/process";
		}
		
	// 신고 처분
	@PutMapping("process")
	@Transactional
	public String process(
			@RequestParam String reportGbn,
			@RequestParam Long userId,
			@RequestParam Long adminId,
			@RequestParam(required = false) Long postId,
			@RequestParam(required = false) Long commentId,
			@RequestParam String reportReasonId,
			@RequestParam(required = false) String content,
			@RequestParam(required = false) String sanctionTime
	){
		
		String result = "";
		
		Report reportSave = new Report();
		
		reportSave.setReportGbn(reportGbn);
		reportSave.setUserId(userId);
		reportSave.setAdminId(adminId);
		reportSave.setPostId(postId);
		reportSave.setCommentId(commentId);
		reportSave.setReportReasonId(reportReasonId);
		reportSave.setContent(content);
		reportSave.setSanctionTime(sanctionTime);
		
		reportService.reportProcessMain(reportSave);
		
		result = reportService.reportProcessSub(reportSave);
		
		System.out.println("reportGbn : "+reportGbn);
		System.out.println("userId : "+userId);
		System.out.println("adminId : "+adminId);
		System.out.println("postId : "+postId);
		System.out.println("commentId : "+commentId);
		System.out.println("reportReasonId : "+reportReasonId);
		System.out.println("content : "+content);
		System.out.println("sanctionTime : "+sanctionTime);
		
		System.out.println(result);
		
		return "redirect:/admin/report/list";
	}
		
}
