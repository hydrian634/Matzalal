package com.matzalal.web.service;

import com.matzalal.web.entity.Email;
import com.matzalal.web.entity.User;
import com.matzalal.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService{

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Email createMailChangePassword(String email, String name) {
        String tempPassword = getTempPassword();

        System.out.println("임시비밀번호 : " + tempPassword);

        // 보낼 이메일 생성
        Email sendMail = Email.builder()
                .email(email)
                .title(name + "님의 Matzalal 임시비밀번호 안내 이메일 입니다.")
                .content("안녕하세요. Matzalal 임시비밀번호 안내 관련 이메일 입니다." +
                        " [" + name + "]님의 임시 비밀번호는 [" + tempPassword + "] 입니다.")
                .build();

        System.out.println("보낼 이메일 내용 : " + sendMail);
        updateTempPassword(email, tempPassword);

        return sendMail;
    }

    @Override
    public void mailSend(Email sendEmail) {
        System.out.println("이메일 전송 시작");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendEmail.getEmail());
        System.out.println("메시지 이메일 저장");
        message.setFrom("rethonly@gmail.com");
        System.out.println("메시지 발신자 저장");
        message.setSubject(sendEmail.getTitle());
        System.out.println("메시지 제목 저장 ");
        message.setText(sendEmail.getContent());
        System.out.println("메시지 본문 저장 ");

        mailSender.send(message);
        System.out.println("이메일 전송 완료!");
    }

    public void updateTempPassword(String email, String tempPassword) {
        System.out.println("임시비밀번호 데이터 저장 시작");
        String hashedPwd = encoder.encode(tempPassword);
        System.out.println("임시비밀번호(암호화) : " + hashedPwd);

        Long id = userRepository.findByEmail(email).getId();

        User user = User.builder()
                .id(id)
                .pwd(hashedPwd)
                .build();

        // 유저 정보 변경
        userRepository.modify(user);
        System.out.println("임시비밀번호 저장 완료");
    }

    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
