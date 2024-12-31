package com.matzalal.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.entity.User;
import com.matzalal.web.service.CommentService;
import com.matzalal.web.service.PostService;
import com.matzalal.web.service.UserService;

@RestController("adminApiCommuController")
@RequestMapping("admin/api/commu")
public class AdminCommuController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
// ~~~~~~~~~  관리자용  ~~~~~~~~
	
	
	
//	@GetMapping public List<PostView> postList(
//	
//	@RequestParam(name="loc", required=false) Long locationPostId ){
//	System.out.println("el.dataset.id: "+ locationPostId);
//	
//	List<PostView> postList = postService.getViewList(locationPostId); return
//	postList;
//	
//	};
	
	
	
//	@GetMapping("{id}") 
//	public List<PostView> detail(@PathVariable Long id) {
//		System.out.println("commu api"); 
//		List<PostView> post = postService.getById(id);
//		System.out.println(post); 
//		System.out.println("~~~~");
//	
//	return post; 
//	}
	
	@DeleteMapping("posts/{postIds}")
	public boolean delete(@PathVariable Long[] postIds) {
		List<Long> postList = new ArrayList<>();
//		Long longValue = Long.parseLong(ids);
		
		if(postIds.length > 1) {
			for(int i=0; i<postIds.length; i++) {
				postList.add(postIds[i]);
			}
			postService.deletePost(postList);
			System.out.println(postList);
			
		} else {
			System.out.println("1개 선택");
			postService.deletePost(postIds[0]);
		}

		System.out.println("api 삭제완료");
		
		return true;
		
	}
	
	@DeleteMapping("comment/{cmtIds}")
	public boolean deleteCmt(@PathVariable Long[] cmtIds) {
		System.out.println("삭제 테스트");
		
		List<Long> cmtList = new ArrayList<>();
		
		if(cmtIds.length > 1) {
			for(int i=0; i<cmtIds.length; i++) {
				cmtList.add(cmtIds[i]);
			}
			commentService.deleteCmt(cmtList);
			System.out.println(cmtList);
			
		} else {
			System.out.println("1개 선택");
			commentService.deleteCmt(cmtIds[0]);
		}

		System.out.println("api 삭제완료");
		
		return true;
		
	}

	
}
