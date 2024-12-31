package com.matzalal.web.service;

import java.util.Date;
import java.util.List;

import com.matzalal.web.entity.*;
import com.matzalal.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private UserGradeRepository userGradeRepository;

    @Autowired
    private FavRepository favRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ReportReasonRepository reportReasonRespository;

    @Autowired
    private LocationUserRepository locationUserRepository;

    @Override
    public List<User> getList() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    @Override
    public List<User> getList(Integer offset, Integer page, Integer pageSize, String query) {
        // int size = 10;
        // int offset = size * (page-1);
        return repository.findAllByPage(offset, page, pageSize, query);
    }

    @Override
    public User signUp(User user) {
        // 비밀번호 암호화
        String plainPwd = user.getPwd();
        String hashedPwd = encoder.encode(plainPwd);
        System.out.println(hashedPwd);

        user.setPwd(hashedPwd);

        // 회원정보 저장
        repository.save(user);
        // 회원정보 불러오기
        User newUser = repository.last();
        // 권한 저장하기
        Long gradeId = newUser.getGradeId();
        Long userId = newUser.getId();

        UserGrade userGrade = UserGrade.builder()
                .userId(userId)
                .gradeId(gradeId)
                .build();

        userGradeRepository.save(userGrade);

        return newUser;
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean hasEmail(String email) {
        int cnt = repository.findEmail(email);
        System.out.println("이메일 개수 : " + cnt);

        if (cnt == 1) {
            return true;
        } else
            return false;
    };

    @Override
    public User getByEmail(String query) {
        return repository.findByEmail(query);
    }

    @Override
    public boolean hasIdByAlias(String nickname) {
        return repository.hasIdByAlias(nickname);
    }

    @Override
    public UserView getUserViewById(Long id) {
        return repository.findUserViewById(id);
    }

    // ~~~~~ 관리자용 ~~~~~~ //

    @Override
    public Integer countUser() {
        int count = repository.count();
        return count;
    }

    @Override
    public void edit(User user) {
        if (user.getPwd() != null) {
            String plainPwd = user.getPwd();
            String hashedPwd = encoder.encode(plainPwd);
            System.out.println("암호 변경요청시 인코딩된 암호 :" + hashedPwd);

            user.setPwd(hashedPwd);
        }
        // 회원 등급 수정

        if (user.getGradeId()!= null) {
            userGradeRepository.modify(user);
        }

        // 이미지 수정
        System.out.println("editimp");
        repository.modify(user);


    }

    @Override
    public boolean delete(Long id) {
        int rowCount = repository.delete(id);

        if (rowCount == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hasName(String name) {
        return repository.hasIdByName(name);
    }

    @Override
    public boolean hasPhone(String phone) {
        return repository.hasIdByPhone(phone);
    }

    @Override
    public User findIdByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<PostUserLikeCommentView> getPostViewById(long id) {
        return repository.findPostViewById(id);
    }

    @Override
    public List<Grade> getGrade() {
        List<Grade> grade = gradeRepository.findAll();
        return grade;
    }

    @Override
    public List<ReportReason> getReasonList() {
        List<ReportReason> reasonList = reportReasonRespository.findAll();
        return reasonList;
    }

    @Override
    public List<LocCategory> getCategoryList() {
        List<LocCategory> locCategory = locationUserRepository.findAll();

        return locCategory;
    }

    @Override
    public void deleteUser(Long id) {
        System.out.println("삭제중");
        repository.delete(id);
    }

    @Override
    public boolean deleteUser(List<Long> idList) {
        System.out.println("여러 아이디 삭제중");
        repository.deleteUsers(idList);
        return false;
    }

    @Override
    public List<FavView> getFavViewByUserId(Long id) {
        System.out.println(favRepository.findViewByUserId(id));
        return favRepository.findViewByUserId(id);
    }

    @Override
    public boolean hasForFindId(User user) {
        int result = repository.findForFindId(user);

        if (result == 0) {
            return false;
        } else
            return true;
    }

    @Override
    public User getByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public boolean hasForFindPwd(User user) {
        int result = repository.findForFindPwd(user);

        if (result == 0) {
            return false;
        } else
            return true;
    }

    @Override
    public boolean hasAlias(String query) {
        int cnt = repository.findAlias(query);
        System.out.println("닉네임 개수 : " + cnt);

        if (cnt == 1) {
            return true;
        } else
            return false;
    }

    // 회원 활동 정지
    @Override
    public void inactiveUser(String email) {
        repository.userSanction(email);
    }

    // 회원 정지 해제
    @Override
    public void activeUser(String email) {
        repository.userSanctionCancel(email);
    }

    // 회원 활동 정지 기간 확인
    @Override
    public Date getSancTime(String email) {
        Date userSancTime = repository.userSanctionTime(email);
        return userSancTime;
    }

    @Override
    public LocationUser getlocById(Integer locId) {
        return locationUserRepository.findById(locId);
    }

}