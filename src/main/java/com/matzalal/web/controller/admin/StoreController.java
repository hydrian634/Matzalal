package com.matzalal.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminStoreController")
@RequestMapping("/admin/store")
public class StoreController {

	@RequestMapping("list")
	public String index() {
		return "/admin/store/list";
	}
	
	@RequestMapping("review")
	public String review() {
		return "/admin/store/review";
	}
	
	@RequestMapping("detail")
	public String detail() {
		return "/admin/store/detail";
	}
	
}
