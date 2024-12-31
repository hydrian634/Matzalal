package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder
public class Post {
	
	private Long postId;
	private Long userId;
	private Long areaId;
	private String title;
	private String content;

	private String img1;
	private String img2;
	private String img3;
	
	private Integer hit;
	private String createdDate;
	
}
