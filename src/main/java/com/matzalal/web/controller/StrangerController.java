package com.matzalal.web.controller;

import com.matzalal.web.entity.User;
import com.matzalal.web.service.StrangerService;
import com.matzalal.web.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("StrangerController")
@RequestMapping("stranger")
public class StrangerController {

    @Autowired
    private UserService service;

    @GetMapping("login")
    public String login() {
        return "stranger/login";
    }


//    @PostMapping("login")
//    public String login(
//            String email,
//            String pwd,
//            @RequestParam("return-url") String returnURL,
//            HttpSession session
//    ) {
//        // 로그인 유효성 검사
//        if (strangerService.isValid(email, pwd)) {
//            User user = userService.getByEmail(email);
//            session.setAttribute("user", user);
//
//            System.out.println("returnURL :::::" + returnURL);
//
//            // 리턴URL 유무 확인
//            if (returnURL != null && returnURL != ""){
//                System.out.println("리턴 URL있음 ");
//                return "redirect:" + returnURL;
//            }
//            return "redirect:/user/mypage?id=" +user.getId();
//        }
//
//        // 로그인 유효성 검사 실패시 페이지 리턴
//        return "redirect:login?error";
//
//    }

    @GetMapping("signup")
    public String signup(Model model) {

        List<User> list = service.getList();

        model.addAttribute("list", list);

        return "stranger/reg_user";
    }

    @PostMapping("signup")
    public String signup(
            String email,
            String password,
            String name,
            String phone,
            String alias,
            @RequestParam("location-id") Long locationId
    ){

        User user = User.builder()
                .email(email)
                .name(name)
                .pwd(password)
                .phone(phone)
                .alias(alias)
                .locationId(locationId)
                .build();

        System.out.println(user);

        User newOne = service.signUp(user);



        System.out.println("회원가입 완료!!!");
        System.out.println(newOne);
        return "redirect:login";
    }

    @RequestMapping("reg/agree")
    public String regAgree() {
        return "stranger/reg_agree";
}

    // ================================아이디 찾기=============================

    @RequestMapping("findid")
    public String findid() {

        return "stranger/findid";
    }

    // ================================비밀번호 찾기=============================
    @RequestMapping("findpw")
    public String findpw() {
        return "stranger/findpw";
    }

    @RequestMapping("user-policy")
    public String userPolicy() {
        return "stranger/user-policy";
    }




}

