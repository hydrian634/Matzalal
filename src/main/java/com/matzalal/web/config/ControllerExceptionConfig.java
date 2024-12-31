package com.matzalal.web.config;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionConfig {

    @ExceptionHandler(Exception.class)
    public String error(Exception e, Model model){

        model.addAttribute("msg","전역 에러 죄송합니다!");
        return "error";
    }
}
