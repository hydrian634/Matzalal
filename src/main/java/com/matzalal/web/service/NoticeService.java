package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.Notice;

public interface NoticeService {
	List<Notice> getList();
	
	int countNotice();
	
	List<Notice> getListByPage(Integer offset, Integer page, Integer size, Long noticeId, String query);
	
	Notice getById(Long noticeId);
	
	// 관리자 시스템
	// 공지사항 등록
	Notice add(Notice notice);

	// 공지사항 수정
	void edit(Notice notice);

	// 공지사항 1개 삭제
	void deleteNotice(Long noticeId);

	// 공지사항 여러개 삭제
	void deleteNotice(List<Long> noticeList);

}
