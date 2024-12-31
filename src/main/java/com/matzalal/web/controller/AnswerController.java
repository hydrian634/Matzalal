//package com.matzalal.web.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.matzalal.web.entity.Answer;
//import com.matzalal.web.service.AnswerService;
//
//@Controller
//@RequestMapping("/qna/answer")
//public class AnswerController {
//
//	@Autowired
//	private AnswerService service;
//
//	
//	// =========================등록 페이지========================= //
//	
//	@GetMapping("reg")   //url 인터넷 주소
//	public String reg() {
//		return "qna/answer/reg";   //템플릿 경로
//	}	
//	
//	@PostMapping("reg")
//	public String reg(
//			String title,
//			String content,
//			@RequestParam(name="open") Integer open,
//			@RequestParam(name="image-file", required=false) String img,
//			Long userId
//			) {
//				System.out.println("open:" + open);
//				System.out.println("qna본문:" + content);
//				Answer answer = Answer.builder()
//						.title(title)
//						.content(content)
//						.open(open)
//						.img(img)
//						.userId(3L)
//						.build();
//		
//		System.out.println("answer:" + answer);
//		service.add(answer);
//		return "redirect:./list";      //돌아갈 주소로 수정해주기.
//	}
//	
//	@RequestMapping("list")
//	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
//		int size = 10; // 페이지 당 아이템 수
//		int offset = (page - 1) * size; // 시작 인덱스
//		System.out.println("문자열1");
//		List<Answer> list = service.getListByPage(offset, page, size, null, null);
//		System.out.println("문자열2");
//		int count = service.countQna();
//		int pageCount = count / size;
//		
//		model.addAttribute("list", list);
//		model.addAttribute("count", count);
//		model.addAttribute("currentPage", page);
//		model.addAttribute("pageCount", pageCount);
//		System.out.println(list);
//		System.out.println("QnA 총 "+count+"건");
//
//		return "qna/question/list";
//	}
//
//	@RequestMapping("detail")
//	public String detail(Model model, 
//			@RequestParam(name="answer-id") Long answerId
//			//@RequestParam(name="prev-question-id") Long prevquestionId,
//			//@RequestParam(name="next-question-id") Long nextquestionId
//			){
//		
//		
//		Answer answer = service.getById(answerId);
//		System.out.println("answer ID? " +answerId);
//		/*
//		//prevquestionId = questionId - 1;
//		//nextquestionId = questionId + 1;
//		//System.out.println("이전 노티스 ID? " +prevquestionId);
//		//System.out.println("다음 노티스 ID? " +nextquestionId);
//		*/
//		
//		model.addAttribute("answer", answer);
//		
//		System.out.println("answer 다 담아오니? " +answer);
//
//		return "qna/answer/detail";
//	}
//	
//	
//	
//	
//	
//	
//	
//	// =========================수정 페이지========================= //
//	
//	@GetMapping("edit")
//	public String getEdit(Long answerId, Model model) {
//		System.out.println(answerId);
//		Answer answer = service.getById(10L);
//		model.addAttribute("answer", answer);
//		return "qna/answer/edit";
//	}
//
//	@PutMapping("edit")
//	public String update(Answer answer) {
//
//		System.out.println("answer"+answer);
//		service.edit(answer);
//
//		return "redirect:./list";
//	}
//	
//
//	
//	
//	
////	@RequestMapping("detail")
////	public String detail(Model model, 
////			//HttpServletRequest request
////			@CookieValue String test
////			) {
////		System.out.println(test);
////		
////		Qna qna = service.getById(2);
////		model.addAttribute("qna", qna);
////		
////		return "qna/detail";
////	}
//	
//	
//	
//}
