package com.matzalal.web.service;

import com.matzalal.web.entity.Celeb;
import com.matzalal.web.entity.CelebRestView;
import com.matzalal.web.entity.RecomView;

import java.util.List;

public interface CelebRestService{

    List<CelebRestView> getCelebListView(Integer page, String query, Long categoryId);

    List<CelebRestView> getByCelebId(long id);

    List<CelebRestView> getCelebListViewById(Long celebId);

    List<CelebRestView> getViewAll(Long celebId);


//    List<Celeb> findAllCeleb();
}
