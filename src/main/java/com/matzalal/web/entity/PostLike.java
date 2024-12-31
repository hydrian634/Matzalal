package com.matzalal.web.entity;


import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;


@Data
@Builder
@AllArgsConstructor
public class PostLike {
	
	private Long userId;
	private Long postId;
	
}
