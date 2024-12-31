package com.matzalal.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.entity.Notice;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.service.NoticeService;

@RestController("apiNoticeController")
@RequestMapping("/api/notices")
public class NoticeController {
		
		@Autowired
		private NoticeService service;
		
//========================== 목록 제공 API ==========================//
	@GetMapping
    public List<Notice> list(
    	@RequestParam(defaultValue = "1") int page,
    	@RequestParam(name = "q", required = false) String query
    	) {
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		List<Notice> list = service.getListByPage(offset, page, size, null, query); //노티스아이디 필요없음. why? 넣으면 걔만 출력되기 때문.
		int count = service.countNotice();
		int pageCount = count / size;
		
		System.out.println("노티스list API 검색 컨트롤러" +list);
		System.out.println("검색어: "+query);
        return list;
    }
}