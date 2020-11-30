package com.atguigu.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{ //资源类

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void incerment() throws Exception
    {
        lock.lock();
        try
        {
            //1.判断
            while(number != 0 ){
                //等待不能生产
                condition.await();
            }

            //2.干活
            number ++ ;
            System.out.println(Thread.currentThread().getName() + "\t "+ number);

            //3.通知唤醒
            condition.signalAll();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void decerment() throws Exception
    {
        lock.lock();
        try
        {
            //1.判断
            while(number == 0 ){
                //等待不能生产
                condition.await();
            }

            //2.干活
            number -- ;
            System.out.println(Thread.currentThread().getName() + "\t "+ number);

            //3.通知唤醒
            condition.signalAll();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }


}


/**
 * @author dwb
 * @create 2020-11-30 22:27
 * 传统版的生产者 消费者模式
 * 题目 一个初始值为零的变量，两个线程对其交替操作，一个加1 一个减1 来五轮
 * 线程 操作（方法） 资源类
 * 判断 干活 通知
 * 防止虚假唤醒机制
 *
 */
public class ProdConsumer_TraditionDemo
{
    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(()->{

            for (int i = 1; i <= 5 ; i++) {

                try {
                    shareData.incerment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"AA").start();

        new Thread(()->{

            for (int i = 1; i <= 5 ; i++) {

                try {
                    shareData.decerment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"BB").start();


    }

}
