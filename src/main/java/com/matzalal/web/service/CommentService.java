package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.CommentPostView;
import com.matzalal.web.entity.CommentView;
import com.matzalal.web.entity.PostView;

public interface CommentService {
	
	Comment add (Comment comment);

	List<CommentView> getById(Long postId);

	CommentView getDtlById(Long postId);

	Comment getByIdForEdit(Long commentId);
	
	int countComment();

	List<CommentPostView> getList(Integer offset, Integer page, Integer pageSize, String query);

	void deleteCmt(Long cmtId);

	void deleteCmt(List<Long> cmtList);


	void edit(Comment comment);


	List<CommentView> getViewAllByUserId(Long id);
}