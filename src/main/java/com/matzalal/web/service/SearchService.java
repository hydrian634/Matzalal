package com.matzalal.web.service;

import com.matzalal.web.entity.Rest;
import com.matzalal.web.entity.RestView;

import java.util.List;

public interface SearchService {
//    List<RestView> getViewList(String query);

    List<Rest> getViewList(String query);

}
