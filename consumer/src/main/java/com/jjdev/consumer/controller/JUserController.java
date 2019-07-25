package com.jjdev.consumer.controller;

import com.jjdev.consumer.controller.response.JResponse;
import com.jjdev.consumer.entity.JUser;
import com.jjdev.consumer.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class JUserController {

    @Autowired
    private IUserService userService;

    private static final Logger log = LoggerFactory.getLogger(JUserController.class);

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<JResponse<List<JUser>>> readAll() {

        log.info("Reading all users");

        List<JUser> users = userService.readAll();

        JResponse<List<JUser>> response = new JResponse<>();
        response.setData(users);
        return ResponseEntity.ok(response);
    }

}
