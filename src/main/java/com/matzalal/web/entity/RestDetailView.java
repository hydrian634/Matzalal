package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestDetailView {

    private Long restId;
    private String restName;
    private String dayOpen;
    private String dayClose;
    private String endOpen;
    private String endClose;
    private String addr;
    private Long locationRestId;
    private String area;
    private String phone;
    private Long menuId;
    private Long rating;
    private String bannerImg;
    private String img1;
    private String img2;
    private String img3;
    private Long celebId;
    private String name;
    private Long adminId;
   // private Long userId;

}
