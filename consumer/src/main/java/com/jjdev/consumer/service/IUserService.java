package com.jjdev.consumer.service;

import com.jjdev.consumer.entity.JUser;

import java.util.List;

public interface IUserService {

    JUser create(JUser user);

    List<JUser> readAll();

}
