package com.jjdev.manager.controller;

import com.jjdev.manager.controller.response.JResponse;
import com.jjdev.manager.entity.JUser;
import com.jjdev.manager.dto.JUserDto;
import com.jjdev.manager.service.IUserService;
import com.jjdev.manager.sns.JSNSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class JUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JSNSService snsService;

    private static final Logger log = LoggerFactory.getLogger(JUserController.class);

    @PostMapping
    public ResponseEntity<JResponse<JUser>> create(@Valid @RequestBody JUserDto userDto, BindingResult result) {

        log.info("Creating user: {}", userDto.getEmail());

        JResponse<JUser> response = new JResponse<>();

        if (result.hasErrors()) {
            log.info("Validation erros: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        JUser user = userService.create(userDto.toUser());

        //send message for SNS
        snsService.sendUserNotification(user);

        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/email/{email:.+}")
    public ResponseEntity<JResponse<JUser>> readByEmail(@PathVariable("email") String email) {

        log.info("Searching user by email: {}", email);

        JResponse<JUser> response = new JResponse<>();
        Optional<JUser> user = userService.readByEmail(email);

        if (!user.isPresent()) {
            log.info("User not found for email: {}", email);
            response.getErrors().add("User not found for email: " + email);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(user.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JResponse<JUser>> readById(@PathVariable("id") Long id) {

        log.info("Searching user by id: {}", id);

        JResponse<JUser> response = new JResponse<>();
        Optional<JUser> user = userService.readById(id);

        if (!user.isPresent()) {
            log.info("User not found for id: {}", id);
            response.getErrors().add("User not found for id: " + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(user.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<JResponse<List<JUser>>> readAll() {

        log.info("Reading all users");

        List<JUser> users = userService.readAll();

        JResponse<List<JUser>> response = new JResponse<>();
        response.setData(users);
        return ResponseEntity.ok(response);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<JResponse<JUser>> update(@PathVariable("id") Long id,
                                                   @Valid @RequestBody JUserDto userDto, BindingResult result) {

        log.info("Updating user: {}", userDto.getEmail());

        JResponse<JUser> response = new JResponse<>();

        if (result.hasErrors()) {
            log.info("Validation errors: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        if (!userService.readById(id).isPresent()) {
            log.info("Invalid user id: {}", id);
            response.getErrors().add("Invalid user id: " + id);
            return ResponseEntity.badRequest().body(response);
        }

        userDto.setId(id);
        JUser user = userService.update(userDto.toUser());

        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<JResponse<String>> delete(@PathVariable("id") Long id) {

        log.info("Removing user by id: {}", id);

        JResponse<String> response = new JResponse<>();

        if (!userService.readById(id).isPresent()) {
            log.info("Invalid user id: {}", id);
            response.getErrors().add("Invalid user id: " + id);
            return ResponseEntity.badRequest().body(response);
        }

        this.userService.delete(id);

        return ResponseEntity.ok(new JResponse<>());
    }

}
