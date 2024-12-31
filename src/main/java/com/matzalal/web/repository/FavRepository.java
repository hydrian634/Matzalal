package com.matzalal.web.repository;

import com.matzalal.web.entity.FavView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavRepository {
    int delete(Long restId, Long userId);

    List<FavView> findViewByRestId(Long id);

    List<FavView> findViewByUserId(Long userId);

    int countViewByUserId(Long id);

}
