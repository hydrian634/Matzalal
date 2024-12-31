package com.matzalal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.ReportPost;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.service.ReportPostService;

@Controller
@RequestMapping("/report/post")
public class ReportPostController {

	@Autowired
	private ReportPostService service;

	@GetMapping("reg")   //url 인터넷 주소
	public String reg(Model model) {
		List<ReportReason> list = service.getReasonList();
		model.addAttribute("list", list);
		return "report/post";   //템플릿 경로
	}	
	
	@PostMapping("reg")
	public String reg(
			String content,
			Long reportUserId,
			Long postId,
			@RequestParam(name="reason") Long reportReasonId,
            Authentication authentication
			) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();
        
		System.out.println("reportReasonId:" + reportReasonId);
		System.out.println("content:" + content);
		ReportPost reportPost = ReportPost.builder()
				.content(content)
				.reportUserId(id)
				.postId(18L)
				.reportReasonId(reportReasonId)
				.build();
		
		System.out.println("reportPost:" + reportPost);

		service.add(reportPost);
		
		return "redirect:/commu/main";      //돌아갈 주소로 수정해주기.
	}
	
//	@RequestMapping("list")
//	public String list(Model model) {
//		List<ReportPost> list = service.getList();
//		model.addAttribute("list", list);
//		System.out.println(list);
//		return "report/post";
//	}
	
}
