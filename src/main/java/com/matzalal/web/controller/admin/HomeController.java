package com.matzalal.web.controller.admin;

import com.matzalal.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminHomeController")
@RequestMapping("/admin")
public class HomeController {
	
	@Autowired
	private UserService service;

	@RequestMapping("index")
	public String index(Model model) {
		
		// 전체 회원 수 전달
		int count = service.countUser();
		model.addAttribute("count", count);
		
		return "/admin/index";
	}
	
}
