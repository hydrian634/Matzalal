package com.matzalal.web.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.service.CommentService;
import com.matzalal.web.service.PostService;

import jakarta.servlet.http.HttpServletRequest;

@RestController("apiCommuController")
@RequestMapping("/api/commus")
public class CommuController {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	// ---------------------- 포스팅 목록 제공 API ----------------------
	@GetMapping
	public List<PostView> postList(
			Authentication authentication,
			@RequestParam(name = "q", required = false) String query,
			@RequestParam(name = "loc", required = false) Long locationPostId) {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		List<PostView> list = postService.getPostList(id);


		System.out.println(query);
		List<PostView> postList = postService.getViewList(query, locationPostId);
		postList.get(0).getUserId();

		System.out.println("el.dataset.id: " + locationPostId);
		System.out.println(postList);
		return postList;
	};

	// ---------------------- 포스팅-이미지 등록 API ----------------------

	@PostMapping("posts")
	public Post reg(
			Post post,
			MultipartFile img,
			HttpServletRequest request,
			Authentication authentication) throws IllegalStateException, IOException {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		System.out.println(post);

		 // 현재 로그인한 userId

		String strPath = request.getServletContext().getRealPath("/css/image/commu/post/");
		// 서블릿이 알고 있는 홈디렉토리(=webapp) 에서 해당 폴더 위치 ! 배포할떄도
		System.out.println(strPath);

		File path = new File(strPath);
		if (!path.exists()) // path가 없으면
			path.mkdirs(); // 여러 디렉토리들을 만들어줌.

		File file = new File( strPath + File.separator+ img.getOriginalFilename());
		img.transferTo(file);

		post.setImg1(img.getOriginalFilename());
		post.setUserId(id);

		Post newOne = postService.add(post);
		System.out.println(newOne);
		System.out.println("--------------------------");
		return newOne;

	}

	// ---------------------- 댓글 등록 API ----------------------

	@PostMapping("comments")
	public Comment reg(
			@RequestBody Comment comment,
			Authentication authentication) {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();
		System.out.println(" user ID :::::::::" + id);

		comment.setUserId(id); // 현재 로그인한 userId
		System.out.println("[API Controller] Comment " + comment);

		Comment newOne = commentService.add(comment);

		System.out.println("[API Controller] newOne " + newOne);
		return newOne;
	}

	// ---------------------- 포스팅 삭제 API ----------------------
	@DeleteMapping("posts/{post-id}")
	public void deletePost(
			@PathVariable("post-id") Long postId) {

		System.out.println("api 포스팅 삭제: " + postId);
		postService.delete(postId);
	}

	// ---------------------- 댓 삭제 API ----------------------
	@DeleteMapping("comments/{comment-id}")
	public void deleteComment(
			@PathVariable("comment-id") Long commentId) {

		System.out.println(commentId);
		commentService.deleteCmt(commentId);
	}


}
