package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.CommuRanking;
import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;

public interface PostService {

	

	// 커뮤 메인 조회 
	List<PostView> getPostList(Long currentUserId);
	List<LocCategory> getCategoryList();

	// [랭킹] 조회수 
	List<CommuRanking> getHitBestList();
	
	// [랭킹] 좋아요 
	List<CommuRanking> getLikesBestList();
	
	// 포스팅 등록 
	Post add(Post post);

	// 포스팅 개별조회 
	PostView getById(Long postId);
	
	// [포스팅 수정] 개별조회 
	Post getByIdForEdit(Long postId);

	// 지역별 카테고리 조회시 
	List<PostView> getViewList(String query, Long locationPostId);

	void edit(Post post);

	void delete(Long postId);

	int commentCount(Long postId); // post상세조회 - 댓글 수 출력 
	
	
	//~*~*~*~*~*관리자용~*~*~*~*~*
	
	int countPost();

	List<PostView> getPostByPage(Integer offset, Integer page, Integer pageSize, String query);

	boolean deletePost(List<Long> postIds);

	void deletePost(Long postId);


	int countAllPost();
}
