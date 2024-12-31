package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.ReportComment;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.repository.ReportCommentRepository;
import com.matzalal.web.repository.ReportReasonRepository;

@Service
public class ReportCommentServiceImp implements ReportCommentService {

	@Autowired
	private ReportReasonRepository reportReasonRepository;
	
	@Autowired
	private ReportCommentRepository repository;
	
	@Override
	public List<ReportReason> getReasonList() {
		return reportReasonRepository.findAll();
	}
	@Override
	public List<ReportComment> getList() {
		return repository.findAll();
	}

	@Override
	public ReportComment add(ReportComment reportComment) {
		repository.save(reportComment);
        ReportComment newOne = repository.last();
        return newOne;
	}
}
