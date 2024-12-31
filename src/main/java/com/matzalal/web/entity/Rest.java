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
public class Rest {
    private Long restId;
    private String restName;
    private String plainBusinessBgnHours;
    private String plainBusinessEndHours;
    private String endBusinessBgnHours;
    private String endBusinessEndHours;
    private String addr;
    private Long locationRestId;
    private String phone;
    private Long menuId;
    private String name;
    private Long rating;
    private String bannerImg;
    private String img1;
    private String img2;
    private String img3;
    private Long celebRestId;
    private Long celebId;
    private String celebName;
    private Long adminId;
    private String alias;
    private Long reviewId;
    private Date createdDate;
    private String etc;
}
