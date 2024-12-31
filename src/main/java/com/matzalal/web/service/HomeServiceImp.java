package com.matzalal.web.service;

import com.matzalal.web.entity.*;
import com.matzalal.web.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImp implements HomeService {

@Autowired
private HomeRepository homeRepository;

    @Override
    public List<RecomView> getRecomViewList() {
        List<RecomView> list = homeRepository.findRecomViewAll();
        return list;
    }

//    @Override
//    public List<Review> getReviewList() {
//        List<Review> list = homeRepository.findReview();
//        return list;
//    }

    @Override
    public List<RatingView> getRankingViewList() {
        List<RatingView> list = homeRepository.findRankLimitThree();

        return list;
    }

    @Override
    public List<RatingView> getRankingViewListAll() {
        List<RatingView> list = homeRepository.findRankAll();

        return list;
    }

    @Override
    public List<RatingView> getRankingList() {
        List<RatingView> list = homeRepository.findRankAll();
        System.out.println("일주일간의 별점 순으로 3개의 맛집을 추려냅니다");
        return list;
    }

    @Override
    public List<Rest> getList() {
        List<Rest> list = homeRepository.getList();
        return list;
    }

    @Override
    public List<Review> getReviewList() {
        List<Review> list = homeRepository.getReviewList();
        return list;
    }

    @Override
    public List<Review> getReviewListByRestId(Long restId) {
        List<Review> reviewList= homeRepository.findReviewByRestId(restId);
        return reviewList;
    }

    @Override
    public Review add(Review review) {

        homeRepository.save(review);
        Review newOne = homeRepository.last();

        return newOne;
    }
}
