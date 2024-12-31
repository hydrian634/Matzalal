package com.matzalal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.Question;
import com.matzalal.web.service.QnaService;

@Controller
@RequestMapping("/qna/question")
public class QuestionController {

	@Autowired
	private QnaService service;

	
	// =========================등록 페이지========================= //
	
	@GetMapping("reg")   //url 인터넷 주소
	public String reg() {
		return "qna/question/reg";   //템플릿 경로
	}	
	
	@PostMapping("reg")
	public String reg(
			String title,
			String content,
			@RequestParam(name="open") Integer open,
			@RequestParam(name="image-file", required=false) String img,
			Long userId,
			Authentication authentication
			) {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
	    System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
	    Long id = userDetails.getId();
	    
		System.out.println("open:" + open);
		System.out.println("qna본문:" + content);
		Question question = Question.builder()
				.title(title)
				.content(content)
				.open(open)
				.img(img)
				.userId(id)
				.build();
		
		System.out.println("question:" + question);
		service.add(question);
		return "redirect:./list";      //돌아갈 주소로 수정해주기.
	}
	
	@RequestMapping("list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		System.out.println("문자열1");
		List<Question> list = service.getListByPage(offset, page, size, null, null);
		System.out.println("문자열2");
		int count = service.countQna();
		int pageCount = count / size;
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		System.out.println(list);
		System.out.println("QnA 총 "+count+"건");

		return "qna/question/list";
	}

	@RequestMapping("detail")
	public String detail(Model model, 
			@RequestParam(name="question-id") Long questionId
			//@RequestParam(name="prev-question-id") Long prevquestionId,
			//@RequestParam(name="next-question-id") Long nextquestionId
			){
		
		
		Question question = service.getById(questionId);
		System.out.println("question ID? " +questionId);
		/*
		//prevquestionId = questionId - 1;
		//nextquestionId = questionId + 1;
		//System.out.println("이전 노티스 ID? " +prevquestionId);
		//System.out.println("다음 노티스 ID? " +nextquestionId);
		*/
		
		model.addAttribute("question", question);
		
		System.out.println("question 다 담아오니? " +question);

		return "qna/question/detail";
	}
	
	// =========================수정 페이지========================= //
	@GetMapping("edit")
	public String getEdit(
			@RequestParam(name="question-id") Long questionId, 
			Model model
			//, Authentication authentication
		) {
		System.out.println(questionId);

//		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
//	    System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
//	    Long id = userDetails.getId();
    
		Question question = service.getById(questionId);  //questionId 불러온거다.
		model.addAttribute("question", question);
		return "qna/question/edit";
	}

	@PutMapping("edit")
	public String update(Question question) {
		System.out.println("question"+question);
		service.edit(question);
		return "redirect:./list";
	}
	
	// =========================삭제========================= //

    @GetMapping("delete")
    public String delete(
    		@RequestParam(name="question-id") Long questionId
    ){
        System.out.println("삭제시도"+questionId);
        service.delete(questionId);
        System.out.println(questionId+"삭제완료!");
		return "redirect:./list";
    }

	
	
	
	
	
	
}