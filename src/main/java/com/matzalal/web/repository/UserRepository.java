package com.matzalal.web.repository;

import java.util.Date;
import java.util.List;

import com.matzalal.web.entity.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    List<User> findAll();
    List<User> findAllByPage(int offset, int page, int pageSize, String query);

    int save(User user);

    User findById(Long id);

    User last();

    int findEmail(String email);

    User findByEmail(String email);

    Boolean hasIdByAlias(String alias);

    UserView findUserViewById(Long id);

    UserView findUserViewByEmail(String email);
	
	
// ~!~~~~~~관리자용 ~~~~~//
    
	Integer count();

	void modify(User user);

    int delete(Long id);
    
    void deleteUsers(Long id);
    
    boolean deleteUsers(List<Long> idList);
    
    boolean hasIdByName(String name);

    boolean hasIdByPhone(String phone);

    User findByName(String name);

    User modifyNickName(String nickname);

    List<PostUserLikeCommentView> findPostViewById(long id);

    int findAlias(String alias);

    int findForFindId(User user);

    User findByUser(User user);

    int findForFindPwd(User user);
    
    // 회원 활동정지
	void userSanction(String email);
	
	// 회원 활동정지 해제
	void userSanctionCancel(String email);
	
	// 회원 활동정지 기간 조회
	Date userSanctionTime(String email);
}
