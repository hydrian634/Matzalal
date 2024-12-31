package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Answer {
	private Long answerId;
	private String title;
	private String content;
	private String img;
	private Long adminId;
	private Date createdDate;
	private Long questionId;
	private Long open;
	}
