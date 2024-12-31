package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.Answer;
import com.matzalal.web.entity.Question;

public interface QnaService {

	// =============== 문의글(question) 관련 업무로직 =============
	
	// 문의글 등록
	Question add(Question question);

	// 문의글 목록 조회
	List<Question> getList();

	// 문의글 개수 조회
	int countQna();

	// 문의글 페이지별 목록조회
	List<Question> getListByPage(Integer offset, Integer page, Integer size, Long questionId, String query);

	// 문의글 아이디 조회
	Question getById(Long questionId);

	// 문의글 수정
	void edit(Question question);
	
	void delete(Long questionId);

	// 문의글 1개 삭제
	void deleteQna(Long questionId);

	// 문의글 다중 삭제
	void deleteQna(List<Long> QnaList);

	
	// ================= 답변글(answer) 관련 업무로직 ================
	
	// 문의글&답변글 - 문의글 클릭시 답변글 함께 조회
	Question getQnA(Long questionId);	
	
	// 답변글 목록 조회
	List<Answer> getAnswerList();
	
	// 답변글 등록
	Answer add(Answer answer);

	// 답변 수정
	void edit(Answer answer);

	// 답변글 유무 조회
	String hasAnswer(Long questionId);

	
}
