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
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.entity.ReportUser;
import com.matzalal.web.service.ReportUserService;

@Controller
@RequestMapping("/report/user")
public class ReportUserController {

	@Autowired
	private ReportUserService service;
	
	@GetMapping("reg")   //url 인터넷 주소
	public String reg(Model model) {
		List<ReportReason> list = service.getReasonList();
		model.addAttribute("list", list);
		return "report/user";   //템플릿 경로
	}	
	
	@PostMapping("reg")
	public String reg(
			String content,
			Long adminId,
			Long reportUserId,
			Long userId,
			@RequestParam(name="reason") Long reportReasonId,
            Authentication authentication
			) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();
        
		System.out.println("reportReasonId:" + reportReasonId);
		System.out.println("content:" + content);
		ReportUser reportUser = ReportUser.builder()
				.content(content)
				.reportUserId(id)
				.userId(18L)
				.reportReasonId(reportReasonId)
				.build();
		
		System.out.println("reportUser:" + reportUser);

		service.add(reportUser);
		
		return "redirect:/";      //돌아갈 주소로 수정해주기.
	}
	
//	@RequestMapping("list")
//	public String list(Model model) {
//		List<ReportUser> list = service.getList();
//		model.addAttribute("list", list);
//		
//		System.out.println(list);
//		return "report/user";
//	}

}
