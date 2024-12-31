package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.LocCategory;

@Mapper
public interface LocCateRepository {

	List<LocCategory> findAll();

}
