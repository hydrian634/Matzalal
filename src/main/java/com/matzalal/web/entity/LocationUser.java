package com.matzalal.web.entity;


import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class LocationUser {
	
	private Long locationPostId;
	private String area;
	
}
