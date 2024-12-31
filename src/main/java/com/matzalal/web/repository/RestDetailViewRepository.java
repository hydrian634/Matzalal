package com.matzalal.web.repository;

import com.matzalal.web.entity.RestDetailView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface RestDetailViewRepository {
    List<RestDetailView> findRestDetailView(Long restId);

//    RestDetailView findById(Long restId, Long celebId);
    RestDetailView findById(Long restId, Long menuId);
    List<RestDetailView> findMenuById(Long restId);
}
