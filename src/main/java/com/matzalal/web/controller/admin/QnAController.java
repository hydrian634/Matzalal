package com.matzalal.web.controller.admin;

import com.matzalal.web.entity.Answer;
import com.matzalal.web.entity.Question;
import com.matzalal.web.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("adminQnAController")
@RequestMapping("/admin/QnA")
public class QnAController {
	
	@Autowired
	private QnaService qnaService;
	

	// 문의글 목록 조회
	@GetMapping("list")
	public String QnA(
			@RequestParam(defaultValue = "1") int page, Model model
	) {
		int size = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * size; // 시작 인덱스
		List<Question> questionList = qnaService.getListByPage(offset, page, size, null, null);
		int count = qnaService.countQna();
		int pageCount = count / size;
		
		model.addAttribute("qnaList", questionList);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		System.out.println("리스트 중에 어디 눌렀닝?!"+ questionList);
		System.out.println("QNA 총 "+count+"건");
		
		return "/admin/QnA/list";
	}
	
	// 답변글 등록페이지 조회
	@GetMapping("answer")
	public String answer(Long questionId, Model model) {
		System.out.println(questionId);
		
		Question question = qnaService.getById(questionId);
 
//		if(question.getAnswerId() != null) {
//			System.out.println("이미 등록된 답변이 있습니다");
//			return "admin/QnA/list";
//		}
		
		System.out.println("~~~~~~~~~~~~");
		System.out.println(question);
		model.addAttribute("question", question);
	
		return "admin/QnA/answer";
	}

	// 답변글 등록
	@PostMapping("regAnswer")
	public String regAnswer(
			@RequestParam(name="aTitle") String title,
			@RequestParam(name="aContent") String content,
			@RequestParam Long questionId,
			@RequestParam Long adminId
	) {
		System.out.println("답변 등록");
		
		Answer answer = Answer.builder()
							  .title(title)
							  .content(content)
							  .questionId(questionId)
							  .adminId(adminId)
							  .build();
		System.out.println(answer);
		
		qnaService.add(answer);
		
		return "redirect:/admin/QnA/list";
				
	}
	
	
	// 답변 수정 페이지 조회
	@GetMapping("edit")
	public String edit(Long questionId, Model model) {
		System.out.println(questionId);
		
		Question QnA = qnaService.getQnA(questionId);

		System.out.println("~~~~~~~~~~~~");
		System.out.println(QnA);
		model.addAttribute("QnA", QnA);
		
		return "admin/QnA/edit";
	}
	
	// 답변 수정
	@PutMapping("editAnswer")
	public String editAnswer(Answer answer) {
		
		qnaService.edit(answer); 
		System.out.println("수정완료");
		System.out.println(answer);
	
	return "redirect:/admin/QnA/list"; 
	}
	
	

}
