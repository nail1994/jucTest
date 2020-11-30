package com.atguigu.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dwb
 * 题目:实现一个自旋锁
 * 自旋锁的好处：循环笔记获取知道成功为止，没有类似wait的阻塞。
 *
 * @create 2020-11-28 17:07
 */
public class SpinLockDemo
{
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();

        System.out.println(thread.getName() + "\t coming in ----");

        while (!atomicReference.compareAndSet(null,thread)){



        }

    }


    public void myUnlock(){
        Thread thread = Thread.currentThread();

        atomicReference.compareAndSet(thread,null);

        System.out.println(thread.getName() + "\t invoked myUnLock");

    }



    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"AA").start();


        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"BB").start();



    }


}
