package com.matzalal.web.controller.admin;

import com.matzalal.web.entity.Faq;
import com.matzalal.web.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("adminFaqController")
@RequestMapping("/admin/faq")
public class FaqController {
	
	@Autowired
	private FaqService faqService;

	@GetMapping("list")
	public String faq(
			@RequestParam(defaultValue = "1") int page, Model model
	) {
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		List<Faq> faqList = faqService.getListByPage(offset, page, size, null, null);
		int count = faqService.countFaq();
		int pageCount = count / size;
		
		model.addAttribute("faqList", faqList);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		System.out.println(faqList);
		System.out.println("faq 총 "+count+"건");
		
		return "/admin/faq/list";
	}
	
	// faq 등록페이지 조회
	@GetMapping("reg")
	public String reg() {
		System.out.println("faq 등록 페이지 조회");
		return "admin/faq/reg";
	}
	
	// faq 등록
	@PostMapping("reg")
	public String reg(
			String title,
			String content
	) {
		System.out.println("FAQ 등록");
		
		Faq faq = Faq.builder()
							  .title(title)
							  .content(content)
							  .build();
		
		System.out.println(faq);
		
		faqService.add(faq);
		
		return "redirect:/admin/faq/list";
				
	}
	
	// faq 수정할 내용 조회 	
	@GetMapping("edit") 
	public String getEdit(@RequestParam Long faqId, Model model ) { 
	
		System.out.println("QQQ faq 수정 조회");
		Faq faqDtl = faqService.getById(faqId);
	
		model.addAttribute("faqDtl", faqDtl);
	
	return "admin/faq/edit"; 
	}
	
	// faq 수정
	@PutMapping("update") 
	public String update(Faq faq) {
		
		faqService.edit(faq); 
		System.out.println("수정완료");
		System.out.println(faq);
	
	return "redirect:/admin/faq/list"; 
	}
}
