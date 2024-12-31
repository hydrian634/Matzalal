package com.matzalal.web.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matzalal.web.entity.User;
import com.matzalal.web.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController("adminApiUserController")
@RequestMapping("admin/api/users")
public class AdminUserController {

	@Autowired
	private UserService service;
	
	
	
// ~~~~~~~~~  관리자용  ~~~~~~~~
	
	
	@GetMapping
	public List<User> findUser(
			@RequestParam(name="p", defaultValue = "1") Integer page,
			@RequestParam(name="q", required = false) String query
			) {

		int pageSize = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * pageSize; // 시작 인덱스

		List<User> list = service.getList(offset, page, pageSize, query);
		
		return list;
	}
	
	// @ResponseBody
	
//	@GetMapping public List<User> list() {
//	
//		List<User> list = service.getList(); 
//		System.out.println("dd"); 
//		return list; 
//	}
	
//	@GetMapping("{id}") 
//	public User detail(@PathVariable Long id) {
//		System.out.println("cont1"); 
//		User user = service.getById(id);
//		System.out.println(user); 
//		System.out.println("~~~~");
//	
//	return user; 
//	}
	


	@PutMapping
	public void update(
			HttpServletRequest request,
			User user, MultipartFile img, 
			String nEmail, String nPhone, String nAlias
			) throws IllegalStateException, IOException {
		
		String strPath = request.getServletContext().getRealPath("/css/image/user");
		System.out.println(strPath);
		
		// 경로가 없으면 새로 생성해주기
		File path = new File(strPath);
		if(!path.exists()) {
			path.mkdirs();
		}
		{
			// 파일 이름을 가져와서 저장해주기
			File file = new File(strPath+File.separator+img.getOriginalFilename());
			img.transferTo(file);
			
			// 메뉴 객체의 img(string)에 imgFile(multifile)이름을 넣어주기 
			user.setProfileImg(img.getOriginalFilename());
		}	
		
		boolean aliasResult = service.hasAlias(nAlias);
		boolean phoneResult = service.hasPhone(nPhone);
		boolean emailResult = service.hasEmail(nEmail);
		
		if(aliasResult != true){
			user.setAlias(nAlias);
			System.out.println("닉네임을 수정합니다");
		} 
	
		if(phoneResult != true) {
			user.setPhone(nPhone);
			System.out.println("연락처를 수정합니다");
		} 
		
		if(emailResult != true)  {
			user.setEmail(nEmail);
			System.out.println("이메일을 수정합니다");
		}
		
		
		System.out.println(user);
		service.edit(user);
		System.out.println("api 수정 완료");
		
//		ResponseEntity<User> Respentity = new ResponseEntity<>(newOne, HttpStatus.OK);
		
	}
	
	@DeleteMapping("{ids}")
	public boolean delete(@PathVariable Long[] ids) {
		List<Long> idList = new ArrayList<>();
//		Long longValue = Long.parseLong(ids);
		
		if(ids.length > 1) {
			for(int i=0; i<ids.length; i++) {
				idList.add(ids[i]);
			}
			service.deleteUser(idList);
			System.out.println(idList);
			
		} else {
			System.out.println("1개 선택");
			service.deleteUser(ids[0]);
		}
			
//		System.out.println(idList);
		System.out.println("api 삭제완료");
		
		return true;
		
//		return "redirect:/admin/user/list";
	}

	
}
