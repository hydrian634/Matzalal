package com.matzalal.web.service;

import com.matzalal.web.entity.Celeb;

import java.util.List;

public interface CelebListService {
    List<Celeb> getCelebList(Long celebId);
    List<Celeb> getCelebList();

}
