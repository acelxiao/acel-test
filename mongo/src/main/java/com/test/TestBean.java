package com.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

//@Component
@Slf4j
public class TestBean {
    @Resource
    private MongoTemplate mongoTemplate;

//    @PostConstruct
    public void warmUp(){
        for (int i =0; i<30;i ++){

            int finalI = i;
            new Thread(() -> {
                long s = System.currentTimeMillis();
                mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
                log.info(finalI +"--warmUp:"+(System.currentTimeMillis() - s));
            }).start();

        }
    }


    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test1(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test1:"+(System.currentTimeMillis() - s));
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test2(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test2:"+(System.currentTimeMillis() - s));
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test3(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test3:"+(System.currentTimeMillis() - s));
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test4(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test4:"+(System.currentTimeMillis() - s));
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test8(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test8:"+(System.currentTimeMillis() - s));
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test5(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test5:"+(System.currentTimeMillis() - s));
    }


    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test6(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test6:"+(System.currentTimeMillis() - s));
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 20000)
    public void test7(){
        long s = System.currentTimeMillis();
        mongoTemplate.findById("3801211019_3801626304", ImFreeChat.class);
        log.info("test7:"+(System.currentTimeMillis() - s));
    }
}
