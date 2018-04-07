package com.Job;

import com.chatbot.Chatbot;
import com.chatbot.ChatbotManager;
import com.chatbot.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by liufangliang on 2018/2/3.
 */
@Component
@Slf4j
public class LogJob {

    //    @Scheduled(cron = "* * * * * ?")
    public void sendMsg() {
        Chatbot chatbot = ChatbotManager.instance().get(Webhook.LOG);
        chatbot.send("scheduled", "code test");
        log.info("shact");

    }


    public static void sort(List<Integer> ints,List<Integer> result){
        int sum=0;
        for (Integer integer : result) {
            sum+=integer;
        }
        if(sum==10) {
        }
        System.out.println(result);
        if(ints!=null){
            for (int i = 0;i<ints.size();i++) {
                List<Integer> int1=new ArrayList<Integer>(ints);
                int a =int1.remove(i);
                List<Integer> res=new ArrayList<Integer>(result);
                res.add(a);
                sort(int1,res);
            }
        }

    }


    public static void main(String[] args) {
        b b = new b();
        b.a();
        b.b();
        System.out.println(b.a);
        a a = new b();
        a.a();
        System.out.println(a.a+"");
        a ba = new a();

        ba.a();



        List<Integer> int3=new ArrayList<Integer>();
        int3.add(4);
        int3.add(4);

        System.out.println(int3);
        List<Integer> ints=new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            ints.add((int)(Math.random()*10));
        }

        int [][] ad= new int[][]{{1,4,5}};
        int [][] d=ad.clone();
        d[0][1]=199;
        System.out.println(Arrays.toString(d[0]));
//        System.out.println(ints);
//        sort(ints,new ArrayList<Integer>());


    }
}

class a {
    public String  a ="dd";
    public void a() {

        System.out.println("a  a ");
    }
}

class b extends a {
    public int c =10;
    public String  a ="1234";
    public void a() {
        System.out.println("b  a "+a);
    }

    public  void b(){
        System.out.println("b   b "+a);
    }
}



