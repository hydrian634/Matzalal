package com.matzalal.web.service;

import com.matzalal.web.entity.FavView;
import com.matzalal.web.repository.FavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavServiceImp implements FavService{

    @Autowired
    private FavRepository repository;
    @Override
    public boolean delete(Long restId, Long userId) {
        int result = repository.delete(restId, userId);
        if(result ==1)
            return true;
        return false;
    }

    @Override
    public List<FavView> getViewListByRestId(Long id) {
        return repository.findViewByRestId(id);
    }

    @Override
    public List<FavView> getViewListByUserId(Long userId) {
        return repository.findViewByUserId(userId);
    }

    @Override
    public int countFavViewByUserId(Long id) {
        return repository.countViewByUserId(id);
    }

    @Override
    public List<FavView> getFavViewByUserId(Long id) {
        return null;
    }

}
