package com.matzalal.web.service;

import com.matzalal.web.entity.ReviewTotal;
import com.matzalal.web.entity.ReviewView;
import com.matzalal.web.repository.ReviewViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewViewServiceImp implements ReviewViewService {
    @Autowired
    private ReviewViewRepository repository;

    @Override
    public List<ReviewView> getReviewThreeByRestId(Long restId) {
        List<ReviewView> reviewList = repository.findReviewThreeByRestId(restId);
        return reviewList;
    }

    @Override
    public List<ReviewView> getReviewAllByRestId(Long restId) {
        List<ReviewView> reviewList = repository.findReviewAllByRestId(restId);
        return reviewList;
    }

    @Override
    public ReviewTotal getCount(Long restId) {
        ReviewTotal reviewCount = repository.findReviewCount(restId);
        return reviewCount;
    }
}
