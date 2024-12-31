package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewView {

    private Long reviewId;
    private Long userId;
    private Long restId;
    private Long rating;
    private String content;
    private String img;
    private Date createdDate;
    private String restName;
    private String bannerImg;
    private String alias;
    private Long gradeId;
    private String grade;
    private String gradeImg;
}
