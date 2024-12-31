package com.matzalal.web.service;

import com.matzalal.web.entity.RestDetailView;
import com.matzalal.web.repository.RestDetailViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestDetailViewServiceImp implements RestDetailViewService {

    @Autowired
    private RestDetailViewRepository repository;

    @Override
    public List<RestDetailView> getRestDetailView(Long restId) {
        List<RestDetailView> list = repository.findRestDetailView(restId);

        return list;
    }

//    @Override
//    public RestDetailView getRestDetailViewByid(Long restId, Long celebId) {
//        return repository.findById(restId, celebId);
//    }
//    @Override
//    public RestDetailView getRestDetailViewByid(Long restId) {
//    	System.out.println("디테일 페이지 뷰 가져오는중");
//        return repository.findById(restId);
//    }
    @Override
    public List<RestDetailView> getRestMenuById(Long restId) {
        return repository.findMenuById(restId);
    }

	@Override
	public RestDetailView getRestDetailViewByid(Long restId, Long menuId) {
		System.out.println("디테일 페이지 뷰 가져오는중");
        return repository.findById(restId, menuId);
	}
}
