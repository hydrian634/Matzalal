package com.matzalal.web.config.auth;

import com.matzalal.web.entity.GradeView;
import com.matzalal.web.entity.User;
import com.matzalal.web.entity.UserView;
import com.matzalal.web.repository.GradeRepository;
import com.matzalal.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MatzalalUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MatzalalUserDetails userDetails = new MatzalalUserDetails();
        UserView userView = userRepository.findUserViewByEmail(username);

     // 회원 활동정지기간 조회
        Date userSanctime = userRepository.userSanctionTime(username);

        // 현재 시간 조회
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        System.out.println("현재시간: "+now);
        System.out.println("회원정지기간: "+userSanctime);
        
        if(userSanctime != null && now != null) {
        	if(now.after(userSanctime)) {
        		System.out.println("현재 시간은 정지기간 전입니다.");
        		
	        userDetails.setUsername(username); // 이메일 
	        userDetails.setPassword(userView.getPwd());
	        userDetails.setId(userView.getId()); // 유저 아이디 
	//        userDetails.setAlias(userView.getAlias());
	//        userDetails.setPhone(userView.getPhone());
	//        userDetails.setCreatedDate(userView.getDate());
	//        userDetails.setProfileImg(userView.getProfileImg());
	//        userDetails.setPostCount(userView.getPostCount());
	//        userDetails.setCommentCount(userView.getCommentCount());
	//        userDetails.setFavCount(userView.getFavCount());
	
	        // 권한 부여
	        List<GradeView> gradeViewList = gradeRepository.findViewAllById(userView.getEmail());
	
	
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        
	        for(GradeView gv : gradeViewList)
	            authorities.add(new SimpleGrantedAuthority(gv.getGrade()));
	        userDetails.setAuthorities(authorities);
        	} else if(now.before(userSanctime)) {
        		System.out.println("활동 정지된 회원입니다.");
        	} else {
        		System.out.println("날짜가 유효하지 않습니다.");
        	}
        }

        return userDetails;
    }
}
