package com.matzalal.web.repository;

import com.matzalal.web.entity.User;
import com.matzalal.web.entity.UserGrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGradeRepository {


    void save(UserGrade userGrade);

    void modify(User user);
}
