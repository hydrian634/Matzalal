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
public class Report {
	
	private String reportGbn; // 신고 구분
	private Long reportSeq; // 신고 순번
	private Date createdDate; // 신고날짜
	private String content; // 신고내용
	private Integer processYn; // 처리 여부
	private Date processDate; // 처리 날짜
	private Long adminId; // 관리자 아이디
	private String adminName; // 관리자 닉네임
	private String reportUserName; // 신고자 닉네임
	private String tbl; // 신고대상 닉네임 or 게시글 제목 or 댓글 내용
	private Long userId; // 신고대상 아이디
	private String alias; // 신고대상 닉네임
	private String email; // 신고대상 이메일
	private Long postId;  // 신고대상 게시글 코드
	private String title; // 신고대상 게시글 제목
	private Long commentId; // 신고대상 댓글 코드
	private String cmtContent; // 신고대상 댓글 내용
	private String reportReason; // 신고사유
	private String reportReasonId; // 신고 사유 아이디
	private String sanctionTime; // 활동정지기간

}
