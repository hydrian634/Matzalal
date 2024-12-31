package com.matzalal.web.repository;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.PostLike;

@Mapper
public interface CommuLikeRepository {

	void save(PostLike like);

	void delete(Long userId, Long postId);

	PostLike last();
	

}
