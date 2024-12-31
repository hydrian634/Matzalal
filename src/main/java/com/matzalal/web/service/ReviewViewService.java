package com.matzalal.web.service;

import com.matzalal.web.entity.ReviewTotal;
import com.matzalal.web.entity.ReviewView;

import java.util.List;

public interface ReviewViewService {
    List<ReviewView> getReviewThreeByRestId(Long restId);

    List<ReviewView> getReviewAllByRestId(Long restId);

    ReviewTotal getCount(Long restId);
}
