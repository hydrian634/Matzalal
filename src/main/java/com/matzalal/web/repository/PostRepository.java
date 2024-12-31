package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.CommuRanking;
import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;

@Mapper
public interface PostRepository {
	
//  ======================= 커뮤 메인 =======================  
	
//  포스팅 메인 조회 
	List<PostView> findPostWithIsLike(Long currentUserId);
	Post last();
	
	List<PostView> findLikesBest();
//  포스팅 검색 & 카테고리별 조회 
	List<PostView> findViewAll(String query, Long locationPostId);
	
//  포스팅 등록  
	void save(Post post);

//	[랭킹조회] 조회수 인기글 _ 조회수 -> 좋아요 -> 댓글  
	List<CommuRanking> findHitRanking();
	
//	[랭킹조회] 좋아요 인기글 _ 좋아요 -> 댓글  
	List<CommuRanking> findLikesRanking();
	
//  =======================  커뮤 상세 ======================= 

//	[포스팅 상세] 포스팅 출력. 	
	PostView findById(Long postId);

//	[포스팅 상세] 포스팅 댓글 총 개수 . 
	int count(Long postId);

	int Allcount();

//	[포스팅 수정] 포스팅 상세조회 
	Post findByIdForEdit(Long postId);
	
//	[포스팅 수정] 포스팅 수정 저장. 	
	void modify(Post post);
	
	//~~~~ 관리자용~~~~
	int count();

	List<PostView> findAllByPage(Integer offset, Integer page, Integer pageSize, String query);

	void delete(Long postId);

	void deletePosts(List<Long> postList);

}
