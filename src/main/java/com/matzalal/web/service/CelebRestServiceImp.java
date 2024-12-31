package com.matzalal.web.service;

import com.matzalal.web.entity.Celeb;
import com.matzalal.web.entity.CelebRestView;
import com.matzalal.web.entity.RecomView;
import com.matzalal.web.repository.CelebRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CelebRestServiceImp implements CelebRestService{
    @Autowired
    private CelebRestRepository repository;


    @Override
    public List<CelebRestView> getCelebListView(Integer page, String query, Long categoryId) {

        //		int page=1;
        int size=12;
        int offset = size*(page-1);
        List<CelebRestView> list = repository.findViewAll(offset, size, categoryId, query);

        return list;
    }

    @Override
    public List<CelebRestView> getByCelebId(long id) {
        return repository.findByCelebId(id);
    }

    @Override
    public List<CelebRestView> getCelebListViewById(Long celebId) {
        List<CelebRestView> list = repository.findViewByCelebId(celebId);
        return list;
    }

    @Override
    public List<CelebRestView> getViewAll(Long celebId) {
        List<CelebRestView> list = repository.findViewByCelebId(celebId);

        return list;
    }


}
