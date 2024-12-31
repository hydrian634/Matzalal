package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportPost {
	
	private Long postReportId;
	private Date createdDate;
	private String content;
	private Boolean processYn;
	private Date processDate;
	private Long reportUserId;
	private Long postId; //신고 당한 사람 view로 연결해줘야 함.
	private Long reportReasonId;
	private Long adminId;
}
