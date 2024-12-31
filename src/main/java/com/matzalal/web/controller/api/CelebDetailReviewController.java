package com.matzalal.web.controller.api;

import com.matzalal.web.entity.PostView;
import com.matzalal.web.entity.Review;
import com.matzalal.web.entity.ReviewView;
import com.matzalal.web.service.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("ReviewApiController")
@RequestMapping("/api/reviews")
public class CelebDetailReviewController {

        @Autowired
        private HomeService service;

@GetMapping
public List<Review> reviewList(
        @RequestParam(value="restId") Long restId
){
        List<Review> reviewList = service.getReviewListByRestId(restId);

        return reviewList;
}

@PostMapping("create")
public Review reg(@RequestBody Review review) {

        Review newOne = service.add(review);

        System.out.println(newOne);

        return newOne;
        }
}