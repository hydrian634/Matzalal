package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FavView {
    private Long userId;
    private Long restId;
    private String regDate;
    private String content;
    private String restName;
    private Integer rating;
    private String bannerImg;
    private Integer count;
    private Integer reviewCount;

}
