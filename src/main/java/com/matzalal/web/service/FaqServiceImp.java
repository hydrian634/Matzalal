package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Faq;
import com.matzalal.web.repository.FaqRepository;

@Service
public class FaqServiceImp implements FaqService {

	@Autowired
	private FaqRepository repository;
	
	@Override
	public List<Faq> getList() {
		return repository.findAll();
	}

	@Override
	public int countFaq() {
		return repository.count();
	}
	
	@Override
	public List<Faq> getListByPage(Integer page, String query) {  //입력받을 값.
		int size = 10;
		int offset = size*(page-1);
		
		List<Faq> list 
			= repository.getListByPage
				(offset, size, query
						/* page, category, query*/);
		return list;
	}

	// 페이지별 faq 리스트 조회
	@Override
	public List<Faq> getListByPage(Integer offset, Integer page, Integer size, Long faqId, String query) {
		List<Faq> list = repository.getListByPages(offset, page, size, faqId, query);
		return list;
	}

	// faq 등록
	@Override
	public Faq add(Faq faq) {
		System.out.println("faq 등록중~");
		
		repository.save(faq);
		
		// faq 최신글 조회
		Faq newFaq = repository.last();
		
		return newFaq;
	}

	// faq 수정을 위한 아이디별 값 가져오기
	@Override
	public Faq getById(Long faqId) {
			
		return repository.findById(faqId);
	}

	// faq 수정
	@Override
	public void edit(Faq faq) {
		repository.modify(faq);
		
	}

	@Override
	public void deleteFaq(Long faqId) {
		System.out.println("FAQ 1개 삭제중");
		repository.delete(faqId);
		
	}

	@Override
	public void deleteFaq(List<Long> faqList) {
		System.out.println("faq 여러개 삭제중");
		repository.deleteFaqs(faqList);
		
	}
}
