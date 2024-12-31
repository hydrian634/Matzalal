package com.matzalal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.CommentPostView;
import com.matzalal.web.entity.CommentView;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.repository.CommentRepository;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	// ------------------- 댓글 등록  -------------------

	@Override
	public Comment add(Comment comment) {
		commentRepository.save(comment);

		Comment newComment = commentRepository.last();
		System.out.println("댓글저장: " + newComment);
		return newComment;

	}

	// ------------ 포스팅별 댓글 출력 -------------------

	@Override
	public List<CommentView> getById(Long postId) {

		List<CommentView> commentList = commentRepository.findViewAllById(postId);
		return commentList;
	}

	// ------------ 댓글 상세 조회 -------------------
	@Override
	public CommentView getDtlById(Long commentId) {
		CommentView comment = commentRepository.findById(commentId);
		return comment;
	}

	@Override // [댓글 수정 ]
	public Comment getByIdForEdit(Long commentId) {

		Comment comment = commentRepository.findByIdForEdit(commentId);
		return comment;
	}

	@Override 
	public void edit(Comment comment) {
		commentRepository.modifyComment(comment);
		System.out.println("service comment: " + comment);

	}

	@Override
	public List<CommentView> getViewAllByUserId(Long id) {
		return commentRepository.findViewByUserId(id);
	}


	// 댓글 개수 조회
	@Override
	public int countComment() {
		int countComment = commentRepository.countComment();
		
		return countComment;
	}

	@Override
	public List<CommentPostView> getList(Integer offset, Integer page, Integer pageSize, String query) {
		List<CommentPostView> commentList = commentRepository.findAll(offset, page, pageSize, query);
		return commentList;
	}

	@Override
	public void deleteCmt(Long cmtId) {
		System.out.println("댓글 하나 삭제중");
		commentRepository.delete(cmtId);
		
	}

	@Override
	public void deleteCmt(List<Long> cmtList) {
		System.out.println("다수 댓글 삭제중");
		commentRepository.deleteCmts(cmtList);
	}

}
