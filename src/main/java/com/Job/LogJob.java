package com.Job;

import com.chatbot.Chatbot;
import com.chatbot.ChatbotManager;
import com.chatbot.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by liufangliang on 2018/2/3.
 */
@Component
@Slf4j
public class LogJob {

//    @Scheduled(cron = "* * * * * ?")
    public void sendMsg(){
        Chatbot chatbot= ChatbotManager.instance().get(Webhook.LOG);
        chatbot.send("scheduled","code test");

    }

    public void testMsg(){
        log.info("aad");
    }
}
