package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentView {

	private Long commentId;
	private Long userId;
	private Long postId;
	private Long parentId;
	private String createdDate;
	private String content;
	private Long cmtLikeCount;
	private String alias;
	private Long gradeId;
	private String grade;
	private String gradeImg;

}
