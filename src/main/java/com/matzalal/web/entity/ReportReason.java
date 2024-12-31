package com.matzalal.web.entity;


import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ReportReason {
	
	private Long reportReasonId;
	private String category;
	
}
