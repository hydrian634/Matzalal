package com.matzalal.web.repository;

import com.matzalal.web.entity.Rest;
import com.matzalal.web.entity.RestView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchRepository {
    List<Rest> findListByWord(String query);
}
