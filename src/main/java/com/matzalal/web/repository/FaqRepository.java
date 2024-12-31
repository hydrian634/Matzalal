package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Faq;

@Mapper
public interface FaqRepository {

	List<Faq> findAll();

	int count();

	List<Faq> getListByPage(int offset, int size, String query);

	// faq 페이지별 리스트 조회. 생성자 더 많이많이
	List<Faq> getListByPages(Integer offset, Integer page, Integer size, Long faqId, String query);

	// faq 등록
	void save(Faq faq);

	// faq 최신글 조회
	Faq last();

	// faq 수정을 위한 id별 값 가져오기
	Faq findById(Long faqId);

	// faq 수정
	void modify(Faq faq);

	void delete(Long faqId);

	void deleteFaqs(List<Long> faqList);
}
