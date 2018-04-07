package com.controller;

import com.chatbot.Chatbot;
import com.chatbot.ChatbotManager;
import com.chatbot.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liufangliang on 2018/2/2.
 */
@Controller
@Slf4j
public class LogController {

    private Chatbot chatbot=ChatbotManager.instance().get(Webhook.LOG);
    @RequestMapping("/test")
    public void request(HttpServletRequest request){
        log.info("ok");
        chatbot.send("爱回收爬虫","入库20000条记录成功，入库失败请定位问题",new Object[]{"15736873319"},false,true);


        chatbot.send("提醒","最后一个人走不要忘记锁门哈",null,false,false);


        chatbot.send("报警🚔","都看下这是谁都异常",null,true,true);

        chatbot.send("爱回收爬虫","不入库");

    }
    @RequestMapping("/json")
    @ResponseBody
    public Map<String,Object> requestJson(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("status","success");
        return map;
    }

}
