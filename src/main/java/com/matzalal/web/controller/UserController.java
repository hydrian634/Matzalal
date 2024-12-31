package com.matzalal.web.controller;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.*;
import com.matzalal.web.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("UserController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private FavService favService;


    @GetMapping("mypage")
    public String mypage(
            Model model,
            Authentication authentication
    ) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        Long id = userDetails.getId();

        UserView userView = userService.getUserViewById(id);
        model.addAttribute("userView", userView);

        Integer locId = userView.getLocationId();
        LocationUser locUser = userService.getlocById(locId);
        model.addAttribute("loc", locUser);

        return "user/mypage";
    }


    // ================================회원정보 변경=============================
    @RequestMapping("edit")
    public String edit() {

        return "user/edit_user";
    }

    // ================================등급표=============================
    @RequestMapping("grade")
    public String grade() {
        return "user/grade";
    }

    // ================================찜목록 =============================
    @GetMapping("myfav")
    public String fav(
            Model model,
            Authentication authentication
    ) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();

        List<FavView> list = userService.getFavViewByUserId(id);
        System.out.println(list);

        UserView userView = userService.getUserViewById(id);
        model.addAttribute("userView", userView);

        model.addAttribute("list", list);

        return "user/fav";
    }

    // ---------------찜목록 삭제 -------------
    @DeleteMapping("fav")
    public String fav(
            @RequestParam("user-id") Long userId,
            @RequestParam("rest-id") Long restId
    ){
        System.out.println(userId);
        System.out.println(restId);

        boolean result = favService.delete(restId, userId);
        System.out.println("삭제 확인 : " + result);

        return "redirect:./myfav";
    }


    // ================================작성한 글 =============================
    @RequestMapping("mypost")
    public String post(Model model,
                       Authentication authentication
    ) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();
        UserView userView = userService.getUserViewById(id);
        model.addAttribute("userView", userView);

        List<PostUserLikeCommentView> myPostList = userService.getPostViewById(id);
        model.addAttribute("list", myPostList);

        return "user/mypost";
    }

    // ================================작성한 후기 =============================
    @GetMapping("mycomment")
    public String mycomment(
            Model model,
            Authentication authentication
    ) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();

        List<CommentView> list = commentService.getViewAllByUserId(id);
        model.addAttribute("list", list);

        UserView userView = userService.getUserViewById(id);
        model.addAttribute("userView", userView);

        return "user/mycomment";
    }

    // ================================회원탈퇴 =============================
    @RequestMapping("delete")
    public String delete(
            Model model,
            Authentication authentication
    ) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();

        UserView userView = userService.getUserViewById(id);
        model.addAttribute("userView", userView);

        return "user/user_delete";
    }

    @PutMapping("delete")
    public String delete(
            Authentication authentication
    ){
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
        Long id = userDetails.getId();
        System.out.println(id);

        User user = User.builder()
                .id(id)
                .gradeId(5L)
                .build();

        userService.edit(user);

        System.out.println("삭제완료!");

        return "redirect:/";

    }

    // ================================로그아웃 =============================
    @PostMapping("logout")
    public String logout(
            HttpServletResponse response,
            Authentication authentication
    ) {
        MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Cookie cookie = new Cookie(username, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }

    private void expiredCookie(HttpServletResponse response, String cookieName){
    Cookie cookie = new Cookie(cookieName, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);

    }
}