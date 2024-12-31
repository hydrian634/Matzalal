package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.ReportUser;

@Mapper
public interface ReportUserRepository {

	List<ReportUser> findAll();

	void save(ReportUser reportUser);

	ReportUser last();

	// 회원 신고 목록 조회
	List<ReportUser> getListByPage(Integer offset, Integer page, Integer size, Long userReportId, String query);

	// 회원 신고 개수 조회
	int count();

}
