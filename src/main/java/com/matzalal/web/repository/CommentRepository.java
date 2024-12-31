package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.CommentPostView;
import com.matzalal.web.entity.CommentView;

@Mapper
public interface CommentRepository {

	void save(Comment comment); // add

	Comment last(); // add

	List<CommentView> findViewAllById(Long postId);

	CommentView findById(Long commentId); // 포스팅별 댓글 출력

	Comment findByIdForEdit(Long commentId);

	int countComment();

	List<CommentPostView> findAll(Integer offset, Integer page, Integer pageSize, String query);

	void delete(Long cmtId);

	void deleteCmts(List<Long> cmtList);

	void modifyComment(Comment comment);

    List<CommentView> findViewByUserId(Long id);
}
