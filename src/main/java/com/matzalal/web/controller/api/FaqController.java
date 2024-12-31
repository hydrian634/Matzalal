package com.matzalal.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.entity.Faq;
import com.matzalal.web.entity.Notice;
import com.matzalal.web.service.FaqService;

@RestController("apiFaqController")
@RequestMapping("/api/faqs")
public class FaqController {
		
		@Autowired
		private FaqService service;
		
//========================== 목록 제공 API ==========================//
	@GetMapping
    public List<Faq> list(
    	@RequestParam(defaultValue = "1") int page,
    	@RequestParam(name = "q", required = false) String query
    	) {
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		List<Faq> list = service.getListByPage(offset, page, size, null, query); //faq ID 필요없음. why? 넣으면 걔만 출력되기 때문.
		int count = service.countFaq();
		int pageCount = count / size;
		
		System.out.println("Faq list API 검색 컨트롤러" +list);
		System.out.println("검색어: "+query);
        return list;
    }
}
