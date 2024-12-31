package com.matzalal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CelebRestView {
    private Long restId;
    private String restName;
    private String bannerImg;
//    private Integer celebRestId;
    private Long menuId;
    private Long celebId;
    private String celebName;

}
