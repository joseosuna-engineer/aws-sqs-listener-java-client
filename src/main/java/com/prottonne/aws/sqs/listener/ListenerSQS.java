package com.prottonne.aws.sqs.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;
import com.prottonne.aws.sqs.service.MyService;

@Service
@EnableSqs
public class ListenerSQS {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    private MyService myService;

    @SqsListener(value = "${queue.name}")
    public void process(String messageFromQueue) {
        logger.info("queueName={};messageFromQueue={}", queueName, messageFromQueue);
        myService.method(messageFromQueue);
    }

}
