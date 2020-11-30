package com.atguigu.java;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author dwb
 * @create 2020-11-29 21:28
 */
public class CyClicBarrierDemo
{

    public static void main(String[] args) {
        //集齐7个龙珠 召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{

            System.out.println(" *** 召唤神龙 *** ");
        });


        for (int i = 1; i <= 7 ; i++) {
            int finalI = i;
            new Thread(()->{

                System.out.println(Thread.currentThread().getName() + "\t 收集到 " + finalI + "个龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },String.valueOf(i)).start();
        }
    }

}
