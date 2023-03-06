package com.acel;

/**
 * Hello world!
 *
 */
public class App 
{
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
}
