package com.matzalal.web.entity;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
public class Comment {

    private Long commentId;
    private Long userId;
    private Long postId;
    private Long parentId;
    private String createdDate;
    private String content;

}
