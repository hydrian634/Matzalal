package com.matzalal.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.service.QnaService;

@RestController("adminApiQnAController")
@RequestMapping("admin/api/qnas")
public class AdminQnAController {

	@Autowired
	private QnaService qnaService;
	
	
	
// ~~~~~~~~~  관리자용  ~~~~~~~~
	// 답변글 유무 데이터 조회
	@GetMapping("/{questionId}") 
	public ResponseEntity<String> detail(@PathVariable Long questionId) {
		System.out.println("Answer 이동 가능?"); 
		String status = qnaService.hasAnswer(questionId);
		System.out.println(status); 
		System.out.println("~~~~");
	
	return ResponseEntity.ok(status); 
	}
	
	
	
	
	@DeleteMapping("{QnaIds}")
	public boolean delete(@PathVariable Long[] QnaIds) {
		List<Long> QnaList = new ArrayList<>();
		
		if(QnaIds.length > 1) {
			for(int i=0; i<QnaIds.length; i++) {
				QnaList.add(QnaIds[i]);
			}
			qnaService.deleteQna(QnaList);
			System.out.println(QnaList);
			
		} else {
			System.out.println("1개 선택");
			qnaService.deleteQna(QnaIds[0]);
		}

		System.out.println("api 삭제완료");
		
		return true;
	}

}
