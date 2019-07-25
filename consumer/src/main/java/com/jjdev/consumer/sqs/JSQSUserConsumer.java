package com.jjdev.consumer.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjdev.consumer.dto.JUserDto;
import com.jjdev.consumer.entity.JUser;
import com.jjdev.consumer.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class JSQSUserConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(JSQSUserConsumer.class);

    @SqsListener("jjdev-sqs-manager")
    public void userMessageListener(String message, @Header("SenderId") String senderId) {

        LOG.info("Message received: {}, from senderId: {}", message, senderId);

        try {
            JUserDto userDto = objectMapper.readValue(message, JUserDto.class);
            LOG.info("Saving user...");
            userService.create(new JUser(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getAge()));
        } catch (Exception ex) {
            LOG.error("Can't parse user object");
        }
    }

}

