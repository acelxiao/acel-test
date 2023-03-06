package com.acel;

import com.sun.media.jfxmediaimpl.platform.ios.IOSPlatform;
import com.sun.xml.internal.ws.api.pipe.NextAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestModifyList {
    private List<Long> list = new ArrayList<>();

    public void test() {
        int i = 0;
        for (Long aLong : list) {
            i++;
        }
        System.out.println("i:" + i + "    list:"+ list.size());
    }

    public void modify() {
        List<Long> tmp = new ArrayList<>();
        Random random = new Random();
        for (Long i = 0l; i< random.nextInt(100000); i++){
            tmp.add(i);
        }
        list = tmp;
        System.out.println("modify:"+list.size());
    }
}
