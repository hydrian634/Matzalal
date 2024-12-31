package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.Faq;
import com.matzalal.web.entity.Notice;

public interface FaqService {
	List<Faq> getList();
	int countFaq();
	List<Faq> getListByPage(Integer page, String query);
	
	// faq 리스트 조회(페이지 분류)
	List<Faq> getListByPage(Integer offset, Integer page, Integer size, Long faqId, String query);
	
	// faq 등록
	Faq add(Faq faq);
	
	// faq 수정을 위한 아이디별 값 가져오기
	Faq getById(Long faqId);
	
	// faq 수정
	void edit(Faq faq);

	// faq 1개 삭제
	void deleteFaq(Long faqId);
	
	// faq 여러개 삭제
	void deleteFaq(List<Long> faqList);
	
	
	

}
