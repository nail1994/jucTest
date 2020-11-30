package com.atguigu.java;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author dwb
 * @create 2020-11-29 21:41
 */
public class SemaphoreDemo
{
    public static void main(String[] args) {
        //模拟三个车位
        Semaphore semaphore = new Semaphore(3);

        //模拟6个车 抢3个车位
        for (int i = 1; i <= 6; i++) {

            new Thread(()->{

                try {
                    //占
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + "\t  抢到了车位 !");

                    //停3秒
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
