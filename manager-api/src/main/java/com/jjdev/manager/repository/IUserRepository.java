package com.jjdev.manager.repository;

import com.jjdev.manager.entity.JUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUserRepository extends JpaRepository<JUser, Long> {

    @Transactional(readOnly = true)
    JUser findByEmail(String email);

}
