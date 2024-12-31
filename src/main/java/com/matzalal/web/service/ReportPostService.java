package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.ReportPost;
import com.matzalal.web.entity.ReportReason;

public interface ReportPostService {

	List<ReportPost> getList();

	ReportPost add(ReportPost reportPost);

	List<ReportReason> getReasonList();

}
