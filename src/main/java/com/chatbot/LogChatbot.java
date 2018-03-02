package com.chatbot;

/**
 * Created by liufangliang on 2018/2/1.
 */
public class LogChatbot extends Chatbot {
    public LogChatbot(String webhook) {
        super(webhook);
    }


    public static void main(String[] args) {
        LogChatbot chatbot=new LogChatbot(Webhook.LOG);
        chatbot.send("123","chenggong");
    }

}
