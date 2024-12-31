package com.matzalal.web.controller.admin;

import com.matzalal.web.entity.CommentPostView;
import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.service.CommentService;
import com.matzalal.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("adminCommuController")
@RequestMapping("/admin/commu")
public class CommuController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("post")
	public String post(
			@RequestParam(defaultValue = "1") int page,
			Model model
	) {
		// 게시물 개수
		int count = postService.countAllPost();
		
		//page 처리
		int pageSize = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * pageSize; // 시작 인덱스
		int pageCount = count / pageSize;
		
		
		List<PostView> postList = postService.getPostByPage(offset, page, pageSize, null);
		List<LocCategory> locCateList = postService.getCategoryList();
		
		System.out.println(postList);

		model.addAttribute("postList", postList);
		model.addAttribute("locCateList", locCateList);
		model.addAttribute("postCount", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		return "/admin/commu/post";
	}
	
	@GetMapping("comment")
	public String comment(
			@RequestParam(defaultValue = "1") int page,
			Model model
	) {
		// 댓글 개수 조회
		int countComment = commentService.countComment();

		//page 처리
		int pageSize = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * pageSize; // 시작 인덱스
		int pageCount = countComment / pageSize;
		
		// 댓글 리스트 최신순 조회
		List<CommentPostView> commentList = commentService.getList(offset, page, pageSize, null);
		
		
		model.addAttribute("commentList", commentList);

		model.addAttribute("count", countComment);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		
		
		return "/admin/commu/comment";
	}
}
