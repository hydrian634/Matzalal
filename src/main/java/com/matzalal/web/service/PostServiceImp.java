package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.CommuRanking;
import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.repository.CommentRepository;
import com.matzalal.web.repository.LocCateRepository;
import com.matzalal.web.repository.PostRepository;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private LocCateRepository locCateRepository;
	

	@Override
	public Post add(Post post) {

		postRepository.save(post);

		Post newPost = postRepository.last();

		return newPost;
	}


//  --------------------- 커뮤 메인 조회 ---------------------

	@Override
	public List<PostView> getPostList(Long currentUserId) {
		return postRepository.findPostWithIsLike(currentUserId);
	}

	@Override
	public List<LocCategory> getCategoryList() {
		return locCateRepository.findAll();
	}

//	[랭킹조회] 조회수 인기글 _ 조회수 -> 좋아요 -> 댓글  
	@Override
	public List<CommuRanking> getHitBestList() {
		return postRepository.findHitRanking();
	}

//	[랭킹조회] 좋아요 인기글 _ 좋아요 -> 댓글  
	@Override
	public List<CommuRanking> getLikesBestList() {
		return postRepository.findLikesRanking();
	}

// --------------------- 포스팅 검색 & 카테고리별 조회 ---------------------
	@Override
	public List<PostView> getViewList(String query, Long locationPostId) {
		List<PostView> postList 
		= postRepository.findViewAll(query, locationPostId);
		
		return postList;
	}

// --------------------- 포스팅 상세조회  ---------------------
//	[포스팅 상세] 포스팅 출력. 
	@Override
	public PostView getById(Long postId) {
		PostView post = postRepository.findById(postId);
		return post;
	}
	
//	[포스팅 상세] 포스팅 댓글 총 개수 . 
	@Override
	public int commentCount(Long postId) {
		int totalComment = postRepository.count(postId);
		return totalComment;
	}
	
//	[포스팅 수정] 포스팅 상세조회 
	@Override
	public Post getByIdForEdit(Long postId) {
		Post post = postRepository.findByIdForEdit(postId);
		return post;
	}
//	[포스팅 수정] 포스팅 수정 저장. 
	@Override
	public void edit(Post post) {
		postRepository.modify(post);
	}


	
	
// ~~~~~관리자용~~~~~~~~~
	@Override
	public int countPost() {
		int count = postRepository.count();
		return count;
	}


	@Override
	public List<PostView> getPostByPage(Integer offset, Integer page, Integer pageSize, String query) {

		return postRepository.findAllByPage(offset, page, pageSize, query);
	}

	@Override
	public void deletePost(Long postId) {
		System.out.println("게시글 하나 삭제중");
		postRepository.delete(postId);
	}

	@Override
	public int countAllPost() {
		return postRepository.Allcount();
	}

	@Override
	public boolean deletePost(List<Long> postList) {
		System.out.println("다수 게시글 삭제중");
		postRepository.deletePosts(postList);
		return false;
	}

	@Override
	public void delete(Long postId) {
		
		 postRepository.delete(postId);
			System.out.println(postId);
	}

}
