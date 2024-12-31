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
public class Question {
	// 문의글 칼럼
	private Long rownum;
	private Long questionId;
	private String title;
	private String content;
	private String img;
	private Date createdDate;
	private Long userId;
	private Long gradeId;
	private String grade;
	private String alias;
	private Integer open;
	private Long answerId;
	
	// 답변글 조회용 칼럼
	private String aTitle;
	private String aContent;
	private String aImg;
	private Date aCreatedDate;
	private String adminAlias;
	
	
	
	
	}