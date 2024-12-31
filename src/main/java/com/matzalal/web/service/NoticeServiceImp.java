package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Notice;
import com.matzalal.web.repository.NoticeRepository;

@Service
public class NoticeServiceImp implements NoticeService {

	@Autowired
	private NoticeRepository repository;
	
	@Override
	public List<Notice> getList() {
		return repository.findAll();
	}

	@Override
	public int countNotice() {
		return repository.count();
	}
	
	@Override
	public List<Notice> getListByPage(Integer offset, Integer page, Integer size, Long noticeId, String query) {  //입력받을 값.

		List<Notice> list = repository.getListByPage(offset, page, size, noticeId, query);
		return list;
	}

	@Override
	public Notice getById(Long noticeId) {
		return repository.findById(noticeId);
	}
	
	// 관리자 시스템

	// 공지사항 등록
	@Override
	public Notice add(Notice notice) {
		System.out.println("공지사항 등록중~");
		
		repository.save(notice);
		
		// 공지사항 최신글 조회
		Notice newNotice = repository.last();
		
		return newNotice;
		
	}

	// 공지사항 수정
	@Override
	public void edit(Notice notice) {
	 	repository.modify(notice);
		
	}

	@Override
	public void deleteNotice(Long noticeId) {
		System.out.println("공지사항 1개 삭제중");
		repository.delete(noticeId);
	}

	@Override
	public void deleteNotice(List<Long> noticeList) {
		System.out.println("공지사항 여러개 삭제중");
		repository.deleteNotices(noticeList);
	}

}
