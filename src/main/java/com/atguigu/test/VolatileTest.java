package com.atguigu.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dwb
 * @create 2020-11-23 20:47
 * 1.0用来测试 可见性
 *
 * 2.0用来测试volatile 原子性
 *       volatile不能保证原子性。
 *       保证数据的完整一致性。
 *
 *   why?为什么没有办法保证原子性?
 *
 *
 *   2.4如何解决原子性？
 *    加syn
 *    用juc下的 AtomicInteger
 *    那AtomicInteger 是如何实现的呢？ （自旋锁cap）
 *
 *
 */
class MyData{  //MyData.java ===> MyData.class ===> JVM字节码
    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    //请注意此时number 是加了volatile关键字
    public /*synchronized*/ void addPlusPlus() {
        number ++ ;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

}


public class VolatileTest
{

    public static void main(String[] args) {

        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        //我们需要等待上面20个线程全部计算完成后，打印看值
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally number value = " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicInteger value = " + myData.atomicInteger);
    }


    //可以保证volatile可见性，及时通知其他线程
    private static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(()->{

            System.out.println(Thread.currentThread().getName() + "\t  start" );

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.addTo60();

            System.out.println(Thread.currentThread().getName() + "\t myData.number = "+myData.number );

        },"AAA").start();


        //number ==0 无线循环
        while (myData.number == 0) {



        }

        System.out.println(Thread.currentThread().getName() + "\t main end " );
    }
}
