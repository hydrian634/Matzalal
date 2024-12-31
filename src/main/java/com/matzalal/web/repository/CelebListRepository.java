package com.matzalal.web.repository;

import com.matzalal.web.entity.Celeb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CelebListRepository {
    List<Celeb> findViewAll(Long celebId);
    List<Celeb> findCategoryList();
}
