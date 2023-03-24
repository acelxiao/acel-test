package com.acel.spring.hotReplaceBean;

import org.springframework.stereotype.Service;

@Service
public class InnerService {

    public String test(){
        return "inner";
    }
}
