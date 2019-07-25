package com.jjdev.manager.sns;

import com.jjdev.manager.entity.JUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class JSNSService {

    @Autowired
    NotificationMessagingTemplate notificationMessagingTemplate;

    private final String SNS_USER = "jjdev-manager-sns";
    private static final Logger log = LoggerFactory.getLogger(JSNSService.class);
    //------------------------------------------------------------------------------------------------------------------

    public void sendUserNotification(JUser user) {
        if (user == null) {
            log.info("Can't send message. User can't be null.");
            return;
        }
        log.info("Sending message: {}", user);
        notificationMessagingTemplate.sendNotification(SNS_USER, user, "Manager user notification");
    }

}