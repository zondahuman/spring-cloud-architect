package org.spring.cloud.architect.lettuce.config;

import java.util.Date;

/**
 * Created by lee on 2019/3/24.
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("------------daemon----------------------------------"+new Date());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true); //设置守护线程
        thread.start(); //开始执行分进程
        System.out.println("sleep-------start-------------------"+ new Date());
        Thread.sleep(10_000);
        System.out.println("sleep-------end-------------------"+ new Date());

    }


    public void test(){

    }


}
