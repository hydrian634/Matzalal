package com.matzalal.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private Long id; 	// userId->id로 변경
	private String pwd;
	private String alias;
	private String email;
	private String phone;
	private String name;
	private Long gradeId;
	private String profileImg;
	private String date; // createdDate -> date 변경
	private Long statusId;
	private Long locationId; // accessLocationId -> locationId 변경
	private Date sanctionTime; // 활동정지기간
}
