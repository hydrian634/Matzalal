package com.matzalal.web.service;

import com.matzalal.web.entity.Email;

public interface EmailService {

    Email createMailChangePassword(String email, String name);

    void mailSend(Email sendEmail);
}
