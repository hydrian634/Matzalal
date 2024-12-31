package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class RestView {
    private Long restId;
    private String restName;
    private Date openTime;
    private Date closeTime;
    private String addr;
    private Long locRestId;
    private String phone;
    private Long menuId;
    private String menuName;
    private Long price;
    private Long rating;
    private String bannerImg;
    private String img1;
    private String img2;
    private String img3;
    private Long celebRestId;
    private Long reviewId;


}
