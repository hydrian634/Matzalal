package com.matzalal.web.controller;

import com.matzalal.web.entity.RatingView;
import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Rest;
import com.matzalal.web.entity.Review;
import com.matzalal.web.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private HomeService service;

	@RequestMapping("index")
	public String index(Model model) {
//		List<RecomView> recomViewList = service.getRecomViewList();
//		List<Review> reviewList = service.getReviewList();
//		List<RatingView> ratingViewList = service.getRankingViewList();
//
//		System.out.println(recomViewList);
//		System.out.println(reviewList);
//
//		model.addAttribute("recom", recomViewList);
//		model.addAttribute("review", reviewList);
//		model.addAttribute("ratingView", ratingViewList);

		List<Rest> list = service.getList();

		List<Rest> randomRecommend = new ArrayList<>();
		// 랜덤값 뽑기
		Random random = new Random();
//		int min = 1;
//		int max = list.size();
//
//		for(int i=0; i<4; i++) {
//			int randomNum = random.nextInt((max-min)+1)+min;
//			randomRecommend.add(list.get(randomNum - 1));
//			System.out.println("랜덤 아이디" + randomNum);
//		}
		// 최대한 중복값 없애면서 랜덤값 뽑기
		int numRecommend = 4; // 추천음식점 개수
		int maxAttempts = 3 * numRecommend;

		while (randomRecommend.size() < numRecommend && maxAttempts > 0) {
			int randomIndex = random.nextInt(list.size());
			Rest selectedRest = list.get(randomIndex);

			// 이미 추천 리스트에 있는지 확인
			if (!randomRecommend.contains(selectedRest)) {
				randomRecommend.add(selectedRest);
			}

			maxAttempts--;
		}

		// 리스트 개수 및 랜덤값
		System.out.println("맛집 목록의 개수 : "+list.size());

		// 모델에 리스트 추가
		model.addAttribute("list", list);
		model.addAttribute("recom", randomRecommend);
		System.out.println("추천리스트 출력 : "+randomRecommend);


		// 최신 리뷰 출력
		List<Review> reviewList = service.getReviewList();
		model.addAttribute("review", reviewList);

		// 랭킹순 3개 맛집 조회(일주일기준)
		List<RatingView> ranking = service.getRankingList();
		model.addAttribute("rankingList", ranking);
		System.out.println("랭킹 리스트 출력 : "+ranking);

		return "index";
	}
	
	@RequestMapping("rank")
	public String rank(Model model) {

		List<RatingView> ranking = service.getRankingViewListAll();

		model.addAttribute("ranking", ranking);


		return "menu/rank";
	}
}
