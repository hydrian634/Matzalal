package com.matzalal.web.service;

import com.matzalal.web.entity.FavView;

import java.util.List;

public interface FavService {

    boolean delete(Long restId, Long userId);

    // 식당별 FavView 조회
    List<FavView> getViewListByRestId(Long id);

    // 유저별 FavView 조회
    List<FavView> getViewListByUserId(Long id);

    int countFavViewByUserId(Long id);

    List<FavView> getFavViewByUserId(Long id);
}
