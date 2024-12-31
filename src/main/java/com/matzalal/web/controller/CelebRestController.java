package com.matzalal.web.controller;

import com.matzalal.web.entity.*;
import com.matzalal.web.service.CelebListService;
import com.matzalal.web.service.CelebRestService;
import com.matzalal.web.service.RestDetailViewService;
import com.matzalal.web.service.ReviewViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/celeb")
public class CelebRestController {

    @Autowired
    private CelebRestService celebRestService;
    @Autowired
    private CelebListService celebListService;
    @Autowired
    private RestDetailViewService service;
    @Autowired
    private ReviewViewService reviewViewService;

//    -----------------------------유명인-----------------------------------
    @RequestMapping("list")
    public String list(Model model){

        List<CelebRestView> celebRestList = celebRestService.getCelebListViewById(null);
        List<Celeb> celebCategoryList = celebListService.getCelebList();
//        List<CelebRestView> CelebRestViewList = celebRestService.getViewAll();

        model.addAttribute("celebRestList", celebRestList);
        model.addAttribute("celebCategoryList", celebCategoryList);

//        model.addAttribute("CelebRestViewList", CelebRestViewList);

        return "menu/celeb-list";
    }
//    -------------------------------맛집 조회--------------------------------------------------------
    @RequestMapping("detail")
    public String detail(@RequestParam(value = "restId") Long restId,
                         @RequestParam(value = "menuId") Long menuId,
                         Model model
                         ) {
        RestDetailView restDetailList = service.getRestDetailViewByid(restId, menuId);
//        RestDetailView restDetailList = service.getRestDetailViewByid(restId, celebId);
        List<RestDetailView> menuList = service.getRestMenuById(restId);
        List<ReviewView> reviewView = reviewViewService.getReviewThreeByRestId(restId);

        System.out.println(restId);
        System.out.println(restDetailList);
        System.out.println(reviewView);
//        model.addAttribute("restId", restId);

        model.addAttribute("restDetail", restDetailList);
        model.addAttribute("reviewView", reviewView);
        model.addAttribute("menuList", menuList);

        return "menu/restDetail/rest_detail";
    }

//    -------------------------------맛집 후기 더보기--------------------------------------------------------

    @RequestMapping("detail/review")
    public String review(@RequestParam(value = "restId") Long restId,
                         Model model
    ) {


        List<ReviewView> reviewView = reviewViewService.getReviewAllByRestId(restId);
        ReviewTotal totalReview = reviewViewService.getCount(restId);
        System.out.println(reviewView);
//        model.addAttribute("restId", restId);

        model.addAttribute("reviewView", reviewView);
        model.addAttribute("total", totalReview);


        return "menu/restDetail/rest_review_detail";
    }

}
