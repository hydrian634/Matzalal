package com.matzalal.web.service;

import com.matzalal.web.entity.User;
import com.matzalal.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrangerServiceImp implements StrangerService{

    @Autowired
    private UserRepository repository;
    @Override
    public boolean isValid(String email, String pwd) {

        User user = repository.findByEmail(email);

        System.out.println(user);

        if(user == null)
            return false;
        else if(!user.getPwd().equals(pwd))
            return false;

        return true;
    }
}
