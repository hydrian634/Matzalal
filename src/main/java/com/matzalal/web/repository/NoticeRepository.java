package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Notice;

@Mapper
public interface NoticeRepository {
	List<Notice> findAll();

	int count();

	List<Notice> getListByPage(int offset, int page, int size, Long noticeId, String query);

	Notice findById(Long noticeId);

	// 관리자 시스템
	
	// 공지사항 등록
	int save(Notice notice);

	// 공지사항 최신글 조회
	Notice last();

	// 공지사항 수정
	void modify(Notice notice);

	// 공지사항 1개 삭제
	void delete(Long noticeId);

	// 공지사항 여러개 삭제
	void deleteNotices(List<Long> noticeList);

	
}
