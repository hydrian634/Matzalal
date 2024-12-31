package com.matzalal.web.service;

import com.matzalal.web.entity.RestDetailView;

import java.util.List;

public interface RestDetailViewService {

    List<RestDetailView> getRestDetailView(Long restId);

//    RestDetailView getRestDetailViewByid(Long restId, Long celebId);
    RestDetailView getRestDetailViewByid(Long restId, Long menuId);
    List<RestDetailView> getRestMenuById(Long restId);
}
