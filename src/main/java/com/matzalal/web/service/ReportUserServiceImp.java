package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.entity.ReportUser;
import com.matzalal.web.repository.ReportReasonRepository;
import com.matzalal.web.repository.ReportUserRepository;

@Service
public class ReportUserServiceImp implements ReportUserService {

	@Autowired
	private ReportReasonRepository reportReasonRepository;
	
	@Autowired
	private ReportUserRepository repository;
	
	@Override
	public List<ReportReason> getReasonList() {
		return reportReasonRepository.findAll();
	}
	
	@Override
	public List<ReportUser> getList() {
		return repository.findAll();
	}
	
	@Override
	public ReportUser add(ReportUser reportUser) {
		repository.save(reportUser);
        ReportUser newOne = repository.last();
        return newOne;
	}

	// 회원 신고목록조회(페이지별)
	@Override
	public List<ReportUser> getListByPage(Integer offset, Integer page, Integer size, Long userReportId, String query) {
		List<ReportUser> list = repository.getListByPage(offset, page, size, userReportId, query);
		return list;
	}

	@Override
	public int countReportUser() {
		return repository.count();
	}

	@Override
	public List<ReportReason> getReason() {
		List<ReportReason> reason = reportReasonRepository.findAll();
		return reason;
	}



}
