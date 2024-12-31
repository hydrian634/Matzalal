package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // builder랑 붙이지 말기 
public class PostView {
	
	private Long postId;
	private String alias;
	private Long userId;
	private Long areaId;
	private String title;
	private String content;
	private String img1;
	private String img2;
	private String img3;
	private Long hit;
	private String createdDate;
	private Long gradeId;
	private String profileImg;
	private Long postLikeCount;
	private Long commentCount;
	private Boolean isLiked;
	private String sanctionTime;

}
