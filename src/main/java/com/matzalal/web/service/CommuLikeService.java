package com.matzalal.web.service;

import com.matzalal.web.entity.PostLike;

public interface CommuLikeService {


	PostLike add(PostLike like);
	
	
	void delete( Long userId, Long postId);

}
