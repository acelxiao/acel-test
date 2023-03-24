package com.acel.spring.hotReplaceBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService implements My {

    @Autowired
    private InnerService innerService;

    @Override
    public String test(){
        System.out.println(innerService.test());
        return "old";
    }
}
