package com.jjdev.consumer.repository;

import com.jjdev.consumer.entity.JUser;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface IUserRepository extends CrudRepository<JUser, String> {

    Optional<JUser> findById(String id);

    List<JUser> findAll();

}