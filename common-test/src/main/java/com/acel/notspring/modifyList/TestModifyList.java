package com.acel.notspring.modifyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestModifyList {

    public static void main( String[] args ) {
        TestModifyList testModifyList = new TestModifyList();

        new Thread(()->{
            for (int i = 0;  i < 20; i++){
                testModifyList.modify();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();

        for (int i = 0; i< 10000;i++){
            testModifyList.test();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

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
