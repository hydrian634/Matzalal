package com.matzalal.web.controller.api;

import com.matzalal.web.entity.Email;
import com.matzalal.web.entity.User;
import com.matzalal.web.service.EmailService;
import com.matzalal.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("apiStrangerController")
@RequestMapping("/api/strangers")
public class StrangerController {
    @Autowired
    private UserService service;

    @Autowired
    private EmailService emailService;

    @GetMapping("findid")
    public boolean matchIdPhone(
            String name,
            String phone
    ){
        System.out.println(name);
        System.out.println(phone);

        User user = User.builder()
                .name(name)
                .phone(phone)
                .build();

        System.out.println("입력한 user" + user);

        boolean result = service.hasForFindId(user);
        System.out.println("id 찾기 결과 (있으면 true):" + result);

        return result;
    }

    @GetMapping
    public String sendPhone(
            String name,
            String phone
    ){
        System.out.println(name);
        System.out.println(phone);

        User user = User.builder()
                .name(name)
                .phone(phone)
                .build();

        System.out.println("입력한 user" + user);

        User getOne = service.getByUser(user);

        if(getOne == null)
            return "아이디를 찾을 수 없습니다.";

        System.out.println("아이디 찾기 가져온 user :" + getOne);

        String email = getOne.getEmail();

        System.out.println(email);

        return email;
    }

    // 비밀번호 찾기 - 이메일 전송
    @PostMapping("findpw")
    public String sendEmail(
            @RequestParam String email,
            @RequestParam String name
    ){
        System.out.println(email);
        System.out.println(name);

        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        Email sendEmail = emailService.createMailChangePassword(email, name);
        emailService.mailSend(sendEmail);
        System.out.println(user);

        return email;
    }

    @GetMapping("findpw")
    public boolean matchEmailName(
            String name,
            String email
    ){
        System.out.println(name);
        System.out.println(email);

        User user = User.builder()
                .name(name)
                .email(email)
                .build();

        System.out.println("입력한 user" + user);

        boolean result = service.hasForFindPwd(user);
        System.out.println("id 찾기 결과 (있으면 true):" + result);

        return result;
    }


}
