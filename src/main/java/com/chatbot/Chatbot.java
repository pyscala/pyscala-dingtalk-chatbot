package com.chatbot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

/**
 * Created by liufangliang on 2018/1/31.
 */
@Slf4j
public  class Chatbot {

    private String WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=29eddf29785a136b8407d9cfd89f0d06cb2c7302b5a90c68e5de6466592f7e08";
    private static HttpClient httpclient = HttpClients.createDefault();

    public Chatbot(String webhook) {
        WEB_HOOK = webhook;
    }

    public void send(String title,String content) {
        String msg=buildText(title,content,null,null,true);
        sendPost(msg);
    }
    public void sendToAll(String title,String content) {
        String msg=buildText(title,content,null,true,false);
        sendPost(msg);
    }
    public void send(String title,String content, Object[] atMobiles, Boolean isAtAll) {
        String msg = buildText(title,content, atMobiles, isAtAll,true);
        sendPost(msg);
    }

    public void printAndSend(String title,String content, Object[] atMobiles, Boolean isAtAll,Boolean isNeedLineMsg) {
        log.info(title,content);
        String msg = buildText(title,content, atMobiles, isAtAll,isNeedLineMsg);
        sendPost(msg);
    }

    /**
     *
     * @param title
     * @param content
     * @param atMobiles
     * @param isAtAll
     * @param isNeedLineMsg
     */
    public void send(String title,String content, Object[] atMobiles, Boolean isAtAll,Boolean isNeedLineMsg) {
        String msg = buildText(title,content, atMobiles, isAtAll,isNeedLineMsg);
        sendPost(msg);
    }


    public String sendPost(String message) {
        HttpPost httppost =null;

        try {
            httppost=new HttpPost(WEB_HOOK);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            StringEntity se = new StringEntity(message, "utf-8");
            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info(result);
            }
        } catch (Exception e) {
            log.info(e + "");
        }finally {
            try {
                if(httppost!=null){
                    httppost.clone();
                }
            }catch (Exception e){
                log.info(e+"");
            }
        }
        return null;
    }

    /**
     * text形式如下：
     * {
     *     "msgtype": "text",
     *     "text": {
     *         "content": "我就是我, 是不一样的烟火"
     *     },
     *     "at": {
     *         "atMobiles": [
     *             "156xxxx8827",
     *             "189xxxx8325"
     *         ],
     *         "isAtAll": false
     *     }
     * }
     *
     * @return
     */
    public String buildText(String title,String content, Object[] atMobiles, Boolean isAtAll,Boolean isNeedLineMsg) {
        StringBuilder message=new StringBuilder("title:").append(title).append("\nmessage:").append(content) ;

        if(isNeedLineMsg){
            String lineMsg = getLineMsg();
            if (lineMsg != null) {
                String[] arr = lineMsg.split("&&");
                if (arr != null && arr.length == 2) {
                    message.append("\nclassName:").append(arr[0]).append("\nlineNo:").append(arr[1]);
                }
            }
        }
        message.append("\n");

        JSONObject object = new JSONObject();
        object.put("msgtype", "text");

        JSONObject cont = new JSONObject();
        cont.put("content", message.toString());
        object.put("text", cont);

        JSONObject atObj = new JSONObject();
        if (atMobiles != null && atMobiles.length > 0) {
            JSONArray array = new JSONArray(Arrays.asList(atMobiles));
            atObj.put("atMobiles", array);
        }
        if (isAtAll != null) {
            atObj.put("isAtAll", isAtAll);
        } else {
            atObj.put("isAtAll", false);
        }
        object.put("at", atObj);
        return object.toJSONString();
    }

    public static String getLineMsg() {
        String result = null;
        try {
            StackTraceElement[] st = new Throwable().getStackTrace();
            for (StackTraceElement s : st) {
                log.info(s.getLineNumber() + s.getClassName());
            }
            result = st[3].getClassName() + "&&" + st[3].getLineNumber();
        } catch (Exception e) {
            log.info("get lineMessage error !" + e);
        }
        return result;
    }

}
