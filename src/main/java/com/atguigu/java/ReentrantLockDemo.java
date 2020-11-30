package com.atguigu.java;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getId() + " \t  --> invoked  sendSMS() ");
        sendMails();
    }

    public synchronized void sendMails(){

        System.out.println(Thread.currentThread().getId() + " \t  ------> invoked  sendMails() ");
    }

    // ---------------------------------------------------

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){

      lock.lock();
      try
      {
          System.out.println(Thread.currentThread().getId() + " \t  ------> invoked  get() ");
          set();
      }catch(Exception e){
          e.printStackTrace();
      }finally{
          lock.unlock();
      }
    }

    public void set(){

        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getId() + " \t  ------> invoked  set() ");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

}


/**
 * @author dwb
 * @create 2020-11-26 10:25
 */
public class ReentrantLockDemo {


    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{

            phone.sendSMS();

        } , "t1").start();

        new Thread(()->{

            phone.sendSMS();

        } , "t2").start();


        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);

        t3.start();
        t4.start();

    }

}
