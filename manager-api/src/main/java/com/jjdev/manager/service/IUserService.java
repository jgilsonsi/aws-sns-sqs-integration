package com.jjdev.manager.service;

import com.jjdev.manager.entity.JUser;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    JUser create(JUser user);

    Optional<JUser> readById(Long id);

    Optional<JUser> readByEmail(String email);

    List<JUser> readAll();

    JUser update(JUser user);

    void delete(Long id);

}
