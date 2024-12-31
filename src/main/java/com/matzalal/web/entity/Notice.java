package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notice {
	
	private Long noticeId;
	private String title;
	private String content;
	private Date createdDate;
	private Date openedDate;
	private Date closedDate;
}
