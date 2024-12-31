package com.matzalal.web.repository;

import com.matzalal.web.entity.Celeb;
import com.matzalal.web.entity.CelebRestView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CelebRestRepository {
    List<CelebRestView> findByCelebId(long id);

    List<CelebRestView> findViewAll(int offset, int size, Long categoryId, String query);

    List<CelebRestView> findViewByCelebId(Long celebId);

    List<CelebRestView> findViewAll();
}
