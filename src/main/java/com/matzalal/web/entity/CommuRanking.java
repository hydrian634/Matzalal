package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommuRanking {
	
	private String title;
	private Long postId;
	private Long likesCount;
	private Long commentCount;
	private String img;
	private Long rownum;
	private Long hit;
 }
