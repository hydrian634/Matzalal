package com.matzalal.web.service;

import com.matzalal.web.entity.RatingView;
import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Rest;
import com.matzalal.web.entity.Review;

import java.util.List;

public interface HomeService {


    List<RecomView> getRecomViewList();

    List<RatingView> getRankingViewList();

    List<RatingView> getRankingViewListAll();

    // 별점 랭킹순 3개 조회
    List<RatingView> getRankingList();

    // 맛집 목록 가져오기
    List<Rest> getList();

    List<Review> getReviewList();

    //맛집 리뷰 가져오기
    List<Review> getReviewListByRestId(Long restId);


    //맛집 리뷰 POST
    Review add(Review review);
}
