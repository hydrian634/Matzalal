package com.matzalal.web.service;

import java.util.Date;
import java.util.List;

import com.matzalal.web.entity.*;

public interface UserService {
    List<User> getList();

    List<User> getList(Integer offset, Integer page, Integer pageSize, String query);

    User signUp(User user);

    User getById(Long id); // User getById(Long userId);

    boolean hasEmail(String email);

    User getByEmail(String query);

    boolean hasIdByAlias(String nickname);

    UserView getUserViewById(Long id);

    /* 관리자 페이지 */
    Integer countUser();

    void edit(User user);

    boolean delete(Long id);

    boolean hasName(String name);

    boolean hasPhone(String phone);

    User findIdByName(String name);

    List<PostUserLikeCommentView> getPostViewById(long id);

    List<Grade> getGrade();

    List<ReportReason> getReasonList();

    List<LocCategory> getCategoryList();

    // 회원 삭제
    void deleteUser(Long id);

    boolean deleteUser(List<Long> idList);

    List<FavView> getFavViewByUserId(Long id);

    boolean hasAlias(String query);

    boolean hasForFindId(User user);

    User getByUser(User user);

    boolean hasForFindPwd(User user);

    // 회원 활동 정지
    void inactiveUser(String email);

    // 회원 정지 해제
    void activeUser(String email);

    // 회원 활동정지 기간 조회하기
    Date getSancTime(String email);

    // 회원 로케이션 조회
    LocationUser getlocById(Integer locId);

}