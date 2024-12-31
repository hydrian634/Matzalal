package com.matzalal.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.PostLike;
import com.matzalal.web.service.CommuLikeService;

@RestController("apiCommuLikeController")
@RequestMapping("/api/commu/likes")
public class CommuLikeController {

	@Autowired
	private CommuLikeService service;

// 좋아요 등록	 API -------------------------------
	
	@PostMapping 
	public PostLike reg(
		@RequestBody PostLike like,
		Authentication authentication) {
		
		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();
		
		like.setUserId(id); // 현재 로그인한 userId 
		PostLike newOne = service.add(like);
		
		System.out.println(newOne);

		return newOne;
	}


//---------------------------------------------------------------------------------------------
	// 좋아요 삭제 API

	
	@DeleteMapping("posts/{post-id}")
	public void delete(
			@PathVariable("post-id") Long postId,
			Authentication authentication){
		
		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();
        
		Long userId= id;
		
		service.delete(userId, postId);
		
	}
		

	
}
