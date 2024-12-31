package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.ReportComment;
import com.matzalal.web.entity.ReportReason;

public interface ReportCommentService {
	
	List<ReportComment> getList();

	ReportComment add(ReportComment reportComment);

	List<ReportReason> getReasonList();

}
