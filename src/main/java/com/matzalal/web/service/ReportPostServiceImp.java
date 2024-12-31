package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.ReportPost;
import com.matzalal.web.entity.ReportReason;
import com.matzalal.web.repository.ReportPostRepository;
import com.matzalal.web.repository.ReportReasonRepository;

@Service
public class ReportPostServiceImp implements ReportPostService {

	@Autowired
	private ReportReasonRepository reportReasonRepository;
	
	@Autowired
	private ReportPostRepository repository;
	
	@Override
	public List<ReportReason> getReasonList() {
		return reportReasonRepository.findAll();
	}
	
	@Override
	public List<ReportPost> getList() {
		return repository.findAll();
	}

	@Override
	public ReportPost add(ReportPost reportPost) {
		repository.save(reportPost);
        ReportPost newOne = repository.last();
        return newOne;
	}

//	@Override
//	public Menu add(Menu menu) {
//		repository.save(menu);
//		Menu newOne = repository.last();
//		return newOne;
//	}
}
