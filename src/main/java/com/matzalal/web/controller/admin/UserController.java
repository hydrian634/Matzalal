package com.matzalal.web.controller.admin;

import com.matzalal.web.entity.Grade;
import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.User;
import com.matzalal.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("AdminUserController")
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserService service;

	// 회원 목록 페이지
	@RequestMapping("list")
	public String user(@RequestParam(defaultValue = "1") int page, Model model) {
		int pageSize = 10; // 페이지 당 아이템 수
		int offset = (page - 1) * pageSize; // 시작 인덱스

		List<User> list = service.getList(offset, page, pageSize, null);
		List<Grade> gradeList = service.getGrade();
		int count = service.countUser();
		int pageCount = count / pageSize;

		System.out.println(list);

		System.out.println("회원은 총:" + count);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("gradeList", gradeList);

		return "admin/user/list";
	}

	// 수정 페이지
	@GetMapping("edit")
	public String getEdit(
			@RequestParam Long id,
			Model model) {
		System.out.println(id);

		User userDtl = service.getById(id);
		List<Grade> gradeList = service.getGrade();
//		List<ReportReason> reportReason = service.getReasonList();
		List<LocCategory> locCateList = service.getCategoryList();

		model.addAttribute("userDtl", userDtl);
		model.addAttribute("gradeList", gradeList);
//		model.addAttribute("reportReason", reportReason);
		model.addAttribute("locCateList", locCateList);
		System.out.println(userDtl);

		return "admin/user/edit";
	}
	
	@PutMapping("update") 
	public String update(
//			@RequestParam("grade") int gradeId,
			User user) {
		
		service.edit(user); 
		System.out.println("수정완료");
		System.out.println(user);
		System.out.println(user.getGradeId());
	
		return "redirect:/admin/user/list"; 
	}
	

//	// 회원 등록 코드
//	@GetMapping("detail")
//	public String reg() {
//		return "/admin/user/detail";
//	}

//	@PostMapping("detail")
//	public String reg(
////			@RequestParam(name="userImg") String profileImg,
//			@RequestParam(name="grade", required=true) Long gradeId,
//			@RequestParam(name="alias", required=true) String alias,
//			@RequestParam(name="email", required=true) String email,
//			@RequestParam(name="pwd", required=true) String pwd,
//			@RequestParam(name="name", required=true) String name,
//			@RequestParam(name="phone", required=true) String phone,
//			@RequestParam(name="status") Long statusId
//			) {
//		
//			User user = User.builder()
//							.profileImg("user.png")
//							.gradeId(gradeId)
//							.alias(alias)
//							.email(email)
//							.pwd(pwd)
//							.name(name)
//							.phone(phone)
//							.statusId(statusId)
//							.build();
//			System.out.println(user);
//			service.add(user);
//					
//		return "redirect:list";
//	}
	

	// 다중선택 삭제를 위해 주석 처리. 리스트 내 삭제 버튼으로 삭제가능
//		@DeleteMapping("delete")
//		public String deleteUsers(@RequestParam(name = "deleteBox") Long id) {
	//
//			service.deleteUser(id);
//			System.out.println("삭제완료");
	//
//			return "redirect:/admin/user/list";
//		}


}