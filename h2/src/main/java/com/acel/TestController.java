package com.acel;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private UserRepository userDao;

    List<User> users = new ArrayList<>();

    @RequestMapping("/test")
    public String test(){
        users.clear();
        userDao.deleteAll();
        for (int i = 1; i<1000; i++){
            String name = "bbb";
            if (i % 100 == 0){
                name = "aaa";
            }
            User user = new User(i, name);
            userDao.save(user);
            users.add(user);
        }

        long start = System.currentTimeMillis();
        for (int j = 0; j<10000; j++){
            List<User> list = userDao.getUsers("aaa", 111);
        }
        long time1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (int j = 0; j<10000; j++) {
            List<User> list2 = new ArrayList<>(1000);
            for (User user : users) {
                if (user.getUserId() == 111 || user.getUserName().equals("aaa")) {
                    list2.add(user);
                }
            }
        }
        long time2 = System.currentTimeMillis() - start;
        System.out.println(time1 + "----" + time2+ "----");
        return 1+"";
    }
}
