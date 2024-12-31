package com.matzalal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matzalal.web.entity.Notice;
import com.matzalal.web.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService service;

	@RequestMapping("list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		List<Notice> list = service.getListByPage(offset, page, size, null, null);
		int count = service.countNotice();
		int pageCount = count / size;
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		System.out.println("리스트 중에 어디 눌렀닝?!"+ list);
		System.out.println("공지사항 총 "+count+"건");
		return "notice/list";
	}
	
	@RequestMapping("detail")
	public String detail(Model model, 
			@RequestParam(name="notice-id") Long noticeId
			//@RequestParam(name="prev-notice-id") Long prevNoticeId,
			//@RequestParam(name="next-notice-id") Long nextNoticeId
			){
		Notice notice = service.getById(noticeId);
		System.out.println("노티스 ID? " +noticeId);
		/*
		//prevNoticeId = noticeId - 1;
		//nextNoticeId = noticeId + 1;
		//System.out.println("이전 노티스 ID? " +prevNoticeId);
		//System.out.println("다음 노티스 ID? " +nextNoticeId);
		*/
		model.addAttribute("notice", notice);
		System.out.println("노티스 다 담아오니? " +notice);

		return "notice/detail";
	}
}