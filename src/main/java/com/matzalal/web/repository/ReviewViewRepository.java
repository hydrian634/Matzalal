package com.matzalal.web.repository;

import com.matzalal.web.entity.ReviewTotal;
import com.matzalal.web.entity.ReviewView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewViewRepository {
    List<ReviewView> findReviewThreeByRestId(Long restId);

    List<ReviewView> findReviewAllByRestId(Long restId);

    ReviewTotal findReviewCount(Long restId);
}
