package com.atguigu.java;

import com.atguigu.java.constant.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author dwb
 * @create 2020-11-29 20:29
 */
public class CountDownLatchDemo
{
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6 ; i++) {

            new Thread(()->{

                System.out.println(Thread.currentThread().getName() + "\t 国，被灭" );
                //走一个 减一个
                countDownLatch.countDown();

            }, CountryEnum.forEach_CountryEnum(i).getRtMessage()).start();
        }
        try {// 当等于0时
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t  ---------秦帝国，一同华夏");

    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6 ; i++) {

            new Thread(()->{

                System.out.println(Thread.currentThread().getName() + "\t 上完自习离开教室" );
                //走一个 减一个
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }
        try {// 当等于0时
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t  班长最后关门走人---------");
    }
}
