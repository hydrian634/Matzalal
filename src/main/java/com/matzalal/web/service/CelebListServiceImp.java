package com.matzalal.web.service;

import com.matzalal.web.entity.Celeb;
import com.matzalal.web.repository.CelebListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CelebListServiceImp implements CelebListService{
    @Autowired
    private CelebListRepository repository;

    @Override
    public List<Celeb> getCelebList(Long celebId) {
        List<Celeb> list = repository.findViewAll(celebId);

        return list;
    }

    @Override
    public List<Celeb> getCelebList() {
        List<Celeb> list = repository.findCategoryList();

        return list;
    }
}
