package com.matzalal.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.entity.Notice;
import com.matzalal.web.service.NoticeService;

@RestController("adminApiNoticeController")
@RequestMapping("admin/api/notices")
public class AdminNoticeController {

	@Autowired
	private NoticeService noticeService;
	
	
	
// ~~~~~~~~~  관리자용  ~~~~~~~~
	
	
	
	@DeleteMapping("{noticeIds}")
	public boolean delete(@PathVariable Long[] noticeIds) {
		List<Long> noticeList = new ArrayList<>();
		
		if(noticeIds.length > 1) {
			for(int i=0; i<noticeIds.length; i++) {
				noticeList.add(noticeIds[i]);
			}
			noticeService.deleteNotice(noticeList);
			System.out.println(noticeList);
			
		} else {
			System.out.println("1개 선택");
			noticeService.deleteNotice(noticeIds[0]);
		}

		System.out.println("api 삭제완료");
		
		return true;
	}

	
//	@GetMapping public List<User> list() {
//	
//		List<User> list = service.getList(); 
//		System.out.println("dd"); 
//		return list; 
//	}
	
//	@GetMapping
//	public boolean detail(@PathVariable Long noticeId,
//			Model model
//	) {
//		System.out.println("수정api"); 
//		noticeService.getById(noticeId);
////		System.out.println(noticeDtl); 
//		System.out.println("~~~~");
//	
//	return true; 
//	}
//	
//
//
//	@PutMapping
//	public Notice update(
//			@RequestBody Notice notice) {
//		System.out.println(notice);
//		Notice editedNotice = noticeService.edit(notice);
//		System.out.println("api 수정");
//		
//		return editedNotice;
//	}

	
}
