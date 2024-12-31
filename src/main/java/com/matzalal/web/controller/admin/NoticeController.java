package com.matzalal.web.controller.admin;

import com.matzalal.web.entity.Notice;
import com.matzalal.web.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller("adminNoticeController")
@RequestMapping("/admin/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	// 공지 목록 조회
	@GetMapping("list")
	public String list(
			@RequestParam(defaultValue = "1") int page, Model model
	) {
	
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		List<Notice> noticeList = noticeService.getListByPage(offset, page, size, null, null);
		int count = noticeService.countNotice();
		int pageCount = count / size;
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		System.out.println("리스트 중에 어디 눌렀닝?!"+ noticeList);
		System.out.println("공지사항 총 "+count+"건");

		return "/admin/notice/list";

	}
	
	// 공지사항 등록페이지 조회
	@GetMapping("reg")
	public String reg() {
		System.out.println("공지사항 등록 페이지 조회");
		return "admin/notice/reg";
	}
	
	
	// 공지사항 등록
	@PostMapping("reg")
	public String reg(
			String title,
			String content,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date openedDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date closedDate
	) {
		System.out.println("공지사항 등록");
		
		Notice notice = Notice.builder()
							  .title(title)
							  .content(content)
							  .openedDate(openedDate)
							  .closedDate(closedDate)
							  .build();
		System.out.println(notice);
		
		noticeService.add(notice);
		
		return "redirect:/admin/notice/list";
				
	}
	

	// 공지사항 수정할 내용 조회 	
	@GetMapping("edit") 
	public String getEdit(@RequestParam Long noticeId, Model model ) { // System.out.println(noticeId);
	
		System.out.println("수정 내용 조회");
	
		Notice noticeDtl = noticeService.getById(noticeId);
	
		model.addAttribute("noticeDtl", noticeDtl);
	
	return "admin/notice/edit"; 
	}
	
	// 날짜 형식에서 에러나서 실패. 날짜 형식을 맞춰도 문자열-날짜 형식이 안맞아서 오류
	// 공지사항 수정
	@PutMapping("update") 
	public String update(Notice notice) {
		
		noticeService.edit(notice); 
		System.out.println("수정완료");
		System.out.println(notice);
	
	return "redirect:/admin/notice/list"; 
	}
	
	
	
	
	
}
