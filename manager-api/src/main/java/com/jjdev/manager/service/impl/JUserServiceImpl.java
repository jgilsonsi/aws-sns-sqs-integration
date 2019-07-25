package com.jjdev.manager.service.impl;

import com.jjdev.manager.entity.JUser;
import com.jjdev.manager.repository.IUserRepository;
import com.jjdev.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JUserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public JUser create(JUser user) {
        return this.userRepository.save(user);
    }

    @Override
    public Optional<JUser> readById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<JUser> readByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public List<JUser> readAll() {
        return userRepository.findAll();
    }

    @Override
    public JUser update(JUser user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
