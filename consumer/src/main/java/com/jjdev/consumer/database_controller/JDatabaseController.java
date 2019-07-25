package com.jjdev.consumer.database_controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.jjdev.consumer.entity.JUser;
import com.jjdev.consumer.sqs.JSQSUserConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JDatabaseController {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private static final Logger LOG = LoggerFactory.getLogger(JSQSUserConsumer.class);

    //------------------------------------------------------------------------------------------------------------------
    @PostConstruct
    public void init() {
        try {
            LOG.info("Creating dynamoDB tables");
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(JUser.class);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            amazonDynamoDB.createTable(tableRequest);
        } catch (Exception ex) {
            LOG.error("Can't create tables: {}", ex);
        }
    }
}
