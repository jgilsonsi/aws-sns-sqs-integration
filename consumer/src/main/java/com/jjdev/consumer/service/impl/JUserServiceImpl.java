package com.jjdev.consumer.service.impl;

import com.jjdev.consumer.entity.JUser;
import com.jjdev.consumer.repository.IUserRepository;
import com.jjdev.consumer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JUserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public JUser create(JUser user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<JUser> readAll() {
        return userRepository.findAll();
    }

}
