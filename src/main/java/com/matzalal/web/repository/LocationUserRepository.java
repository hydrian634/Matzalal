package com.matzalal.web.repository;

import java.util.List;

import com.matzalal.web.entity.LocationUser;
import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.LocCategory;

@Mapper
public interface LocationUserRepository {

	List<LocCategory> findAll();

    LocationUser findById(Integer locId);
}
