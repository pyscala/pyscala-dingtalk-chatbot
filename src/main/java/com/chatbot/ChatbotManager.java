package com.chatbot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liufangliang on 2018/2/1.
 */
public class ChatbotManager {

    private static ChatbotManager chatbotManager;
    private ChatbotManager(){

    }

    public static ChatbotManager instance(){
        if(chatbotManager==null){
            chatbotManager=new ChatbotManager();
        }
        return chatbotManager;
    }

    private static Map<String,Chatbot> chatbots = new HashMap<String, Chatbot>();

    public Chatbot create(String webhookValue){
        return new LogChatbot(webhookValue);
    }
    public Chatbot get(String webhook){
        String md5=Util.getMD5(webhook);
        if(chatbots.keySet().contains(md5)){
            return chatbots.get(md5);
        }else{
            Chatbot chatbot=create(webhook);
            chatbots.put(md5,chatbot);
            return chatbot;
        }
    }
}
