package com.matzalal.web.controller;

import com.matzalal.web.service.RestDetailViewService;
import com.matzalal.web.service.ReviewViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class RestDetailController {

    @Autowired
    private RestDetailViewService service;
    @Autowired
    private ReviewViewService reviewViewService;

//    @RequestMapping("detail")
//    public String detail(Model model,
//                         @RequestParam("") Long restId) {
//
//        RestDetailView restDetailList = service.getRestDetailViewByid(restId);
//        List<Review> reviewList = reviewService.getReviewAllByRestId(restId);
//        System.out.println(restDetailList);
//        System.out.println(reviewList);
//        model.addAttribute("restDetail", restDetailList);
//        model.addAttribute("reviewList", reviewList);
//
//        return "menu/restDetail/rest_detail";
//    }
}
