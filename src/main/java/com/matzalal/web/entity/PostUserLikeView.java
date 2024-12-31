package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostUserLikeView {
    private Long id;
    private String alias;
    private Long userId;
    private Long areaId;
    private String title;
    private String content;
    private String img1;
    private String img2;
    private String img3;
    private Integer hit;
    private String createdDate;
    private Long postLikeCount;
    private Long gradeId;
    private String profileImg;
}
