package com.matzalal.web.controller.commu;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.CommentView;
import com.matzalal.web.entity.CommuRanking;
import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.service.CommentService;
import com.matzalal.web.service.PostService;
import org.springframework.web.multipart.MultipartFile;

@Controller("CommuController")
@RequestMapping("/commu")
public class CommuController {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	// ---------------------- 커뮤 메인 조회 ----------------------

	@RequestMapping("main")
	public String main(
			Model model,
			Authentication authentication) {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long currentUserId = userDetails.getId();

		List<PostView> postList = postService.getPostList(currentUserId);
		System.out.println("커뮤 메인 postList: " + postList);
		model.addAttribute("postList", postList);

		List<LocCategory> locCateList = postService.getCategoryList();
		System.out.println("커뮤 메인 locCateList: 	" + locCateList);
		model.addAttribute("locCateList", locCateList);

		// --------------- 랭킹 조회

		// [랭킹] 조회수 인기글 _조회수-> 좋아요 -> 댓글
		List<CommuRanking> hitBestList = postService.getHitBestList();
		System.out.println("커뮤 메인 조회수 인기글: " + hitBestList);
		model.addAttribute("hitBestList", hitBestList);

		// [랭킹] 좋아요 인기글 _ 좋아요 -> 댓글
		List<CommuRanking> likesBestList = postService.getLikesBestList();
		System.out.println("커뮤 메인 좋아요 인기글 : " + likesBestList);
		model.addAttribute("likesBestList", likesBestList);

		return "commu/main";

	}

	// ---------------------- 포스팅 등록 ----------------------

	@GetMapping("post/create")
	public String postCreate() {
		System.out.println("get");

		return "commu/post/create";
	}

	@PostMapping("post/create")
	public String postCreate(
			Authentication authentication,
			HttpServletRequest request,
			@RequestParam(name = "areaId", required = false) Long areaId,
			@RequestParam(required = true) String title,
			@RequestParam(required = true) String content,
			@RequestParam(name = "img", required = false) MultipartFile img1

	) throws IOException {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		System.out.println(areaId);
		System.out.println(img1);

//		System.out.println(post);
//		postService.add(post);

		// 이미지 저장
		// webapp경로

		String name = img1.getOriginalFilename();

//		String strPath = request.getServletContext().getRealPath("/css/image/user");
//		System.out.println(strPath);

		File path = new File("/css/image/commu/post");
		if (!path.exists())
			path.mkdirs();

		File file = new File( path + File.separator+ img1.getOriginalFilename());
		img1.transferTo(file);

		Post post = Post.builder()
			.userId(id)
			.areaId(areaId)
			.title(title)
			.content(content)
			.img1(name)
			.build();

		System.out.println("저장될 post: " + post);
		postService.add(post);

		return "redirect:/commu/main";
	}

	// ---------------------- 포스팅 상세조회 -> 포스팅, 댓글 출력 ----------------------

	@RequestMapping("post/detail")
	public String postdetail(
			@RequestParam(name = "post-id") Long postId,
			Model model) {

		PostView post = postService.getById(postId);
		List<CommentView> commentList = commentService.getById(postId);
		int count = postService.commentCount(postId);
		// post view 만들어둔 것에서 댓글 총 개수 뽑아오기


		System.out.println("상세조회 postId: " + postId);
		System.out.println("상세조회 post: " + post);
		System.out.println("상세조회 commentList: " + commentList);
		System.out.println("상세조회 commentCount: " + count);

		model.addAttribute("post", post);
		model.addAttribute("commentList", commentList);
		model.addAttribute("count", count);
		return "commu/post/detail";
	}

	// ---------------------- 포스팅 수정 ----------------------

	@GetMapping("post/edit")
	public String postEdit(
			@RequestParam Long postId,
			Model model) {

		Post postDtl = postService.getByIdForEdit(postId);
		List<LocCategory> locCateList = postService.getCategoryList();

		System.out.println("수정 post: " + postDtl);
		System.out.println("수정 locCateList: " + locCateList);

		model.addAttribute("postDtl", postDtl);
		model.addAttribute("locCateList", locCateList);

		return "commu/post/edit";
	}
	// [수정조회] commu/post/edit
	// http://localhost:8000/commu/post/edit?postId=5

	@PutMapping("/postupdate")
	public String update(
			Post post) {

		postService.edit(post);
		System.out.println("수정완료");
		System.out.println(post);
		System.out.println(post.getPostId());

		return "redirect:/commu/main";
	}

	// ---------- 댓글 오른쪽 확장창 클릭 시 -> 댓글 수정 -----------

	@GetMapping("comment/edit")
	public String commentEdit(
			@RequestParam Long commentId,
			Model model) {

		Comment commentDtl = commentService.getByIdForEdit(commentId);
		System.out.println("수정 commentDtl: " + commentDtl);
		model.addAttribute("commentDtl", commentDtl);

		return "commu/comment/edit";
	} // 조회 http://localhost:8000/commu/comment/edit?commentId=5

	@PutMapping("/commentupdate")
	public String update(
			Comment comment) {
		System.out.println("------------------------------------");
		commentService.edit(comment);
		System.out.println(" edit 후  comment: " + comment);

		System.out.println(comment.getPostId());

		System.out.println("수정완료");
		System.out.println(comment.getCommentId());

		return "redirect:/commu/main";
	}

}