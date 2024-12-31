package com.matzalal.web.repository;

import com.matzalal.web.entity.RatingView;
import com.matzalal.web.entity.RecomView;
import com.matzalal.web.entity.Rest;
import com.matzalal.web.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeRepository {
    List<RecomView> findRecomViewAll();

    List<Review> findReview();

    List<RatingView> findRankLimitThree();

    List<RatingView> findRankAll();

    List<Rest> getList();

    List<Review> getReviewList();

    void save(Review review);

    Review last();

    List<Review> findReviewByRestId(Long restId);
}
