package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Faq {
	private Long faqId;
	private String title;
	private String content;
	private Date createdDate;

}
