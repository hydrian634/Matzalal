package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Answer;
import com.matzalal.web.entity.Question;
import com.matzalal.web.repository.AnswerRepository;
import com.matzalal.web.repository.QuestionRepository;

@Service
public class QnaServiceImp implements QnaService {

	@Autowired
	private QuestionRepository repository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	// ============== 문의글 관련 업무로직 구현 ================
	// 문의글 등록
	@Override
	public Question add(Question question) {
        repository.save(question);
        Question addQna = repository.last();
        return addQna;
	}

	// 문의글 목록 조회
	@Override
	public List<Question> getList() {
		return repository.findAll();
	}

	// 문의글 개수 조회
	@Override
	public int countQna() {
		return repository.count();
	}

	// 문의글 페이지별 목록 조회
	@Override
	public List<Question> getListByPage(Integer offset, Integer page, Integer size, Long questionId, String query) {  //입력받을 값.
		List<Question> list = repository.getListByPage(offset, page, size, questionId, query);  //입력받을 값.
		return list;
	}

	// 문의글 아이디별 조회
	@Override
	public Question getById(Long questionId) {
		return repository.findById(questionId);
	}

	// 문의글 수정
	@Override
	public void edit(Question question) {
		repository.modify(question);		
	}

	@Override
	public void delete(Long questionId) {
		repository.delete(questionId);
		System.out.println(questionId);
	}	
	
	// 문의글 1개 삭제
	@Override
	public void deleteQna(Long questionId) {
		System.out.println("문의글 1개 삭제중");
		repository.delete(questionId);
		
	}

	// 문의글 다중 삭제
	@Override
	public void deleteQna(List<Long> QnaList) {
		System.out.println("문의글 여러개 삭제중");
		repository.deleteQnas(QnaList);		
	}
	
	// ==============답변글 관련 업무로직 구현====================

	// 문의글&답변글 - 문의글 클릭시 답변글 함께 조회
	@Override
	public Question getQnA(Long questionId) {
		Question QnA = repository.findQnA(questionId);
		return QnA;
	}
	
	
	// 답변글 목록 조회
	@Override
	public List<Answer> getAnswerList() {
		return answerRepository.findAll();
	}
	
	// 답변글 등록
	@Override
	public Answer add(Answer answer) {
		answerRepository.save(answer);
		Answer addAnswer = answerRepository.last();
        return addAnswer;
	}

	// 답변글 수정
	@Override
	public void edit(Answer answer) {
		answerRepository.modify(answer);
		System.out.println("수정 구현 부분~");
	}

	// 답변글 유무 조회
	@Override
	public String hasAnswer(Long questionId) {
		String result = repository.countAnswer(questionId);
		return result;
	}


	
}
