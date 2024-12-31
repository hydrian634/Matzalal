package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Grade;
import com.matzalal.web.entity.ReportReason;

@Mapper
public interface ReportReasonRepository {

	List<ReportReason> findAll();


}
