package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReportUser {
	
	private Long userReportId;
	private Date createdDate;
	private String content;
	private Boolean processYn;
	private Date processDate;
	private Long adminId;
	private Long reportUserId;
	private Long userId; //신고 당한 사람 view로 연결.
	private Long reportReasonId;
}
