package com.matzalal.web.controller.api;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.User;
import com.matzalal.web.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController("apiUserController")
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("edit")
	public boolean edit(
			@RequestParam(name = "q") String query) {
		System.out.println(query);
		boolean result = service.hasAlias(query);

		System.out.println("중복유무 ::: " + result);
		return result;
	}

	@PutMapping("edit")
	public boolean update(
			// @RequestParam("grade") int gradeId,
			Authentication authentication,
			@RequestBody User user) {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		user.setId(id);

		System.err.println(user);

		service.edit(user);

		User ModiUser = service.getById(id);
		System.out.println("수정 후 유저정보 " + ModiUser);

		System.out.println("수정완료");

		return true;
	}

	@PostMapping
	public String update(
			User user,
			MultipartFile img, // 엔티티의 img 속성명과 같으면 안된다.
			HttpServletRequest request,
			Authentication authentication) throws IOException {
		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		System.out.println("나와아아아아앙아아아아아아아아아아!!!!!!");
		System.out.println(user);
//		// webapp경로
		String strPath = request.getServletContext().getRealPath("/css/image/user");
		System.out.println(strPath);
		// 경로 설정
		File path = new File(strPath);
		if (!path.exists())
			path.mkdirs();

		File file = new File( strPath + File.separator+ img.getOriginalFilename());
		img.transferTo(file);

		user.setProfileImg(img.getOriginalFilename());
		user.setId(id);

		service.edit(user);

		return "변환성공";

	}

	 @GetMapping("reg")
	 public Boolean reg(
	 	@RequestParam(name="q", required = false) String email,
		@RequestParam(name="a", required = false) String alias
	 ){
		if(alias == null) {
			 System.out.println("이메일 검색어:" + email);
			 Boolean hasEmail = service.hasEmail(email);
			 System.out.println("이메일 유무 :" + hasEmail);
			return hasEmail;
		}
		else {
			System.out.println("닉네임 검색어:" + alias);
			Boolean hasAlias = service.hasAlias(alias);
			System.out.println("닉네임 유무 :" + hasAlias);
			return hasAlias;
		 }

	 }

}