package com.acel;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements Hello{
    public String test(){
        return "old";
    }
}
