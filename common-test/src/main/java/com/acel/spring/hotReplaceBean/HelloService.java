package com.acel.spring.hotReplaceBean;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements My {
    @Override
    public String test(){
        return "old";
    }
}
