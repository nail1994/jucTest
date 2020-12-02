package com.atguigu.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dwb
 * @create 2020-12-02 22:31
 *
 * volatile / CAS / atomicInteger / BlockQueue /线程交互 /原子引用
 *
 */
class MyResource{

    //默认开启 进行生产和消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {

        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception
    {
        String data = null;
        boolean retValue;
        while (FLAG){

            data = atomicInteger.incrementAndGet() + "";

            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName() + "\t 插入队列"+ data + "成功");
            }else{
                System.out.println(Thread.currentThread().getName() + "\t 插入队列"+ data + "失败");
            }

            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "\t 叫停了，表示FLAG = false 生产动作结束 !");
    }

    public void myConsumer() throws Exception
    {
        String result = null;
        boolean retValue;
        while (FLAG) {

            result = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;

                System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟没有取到，消费退出! ");
                System.out.println();
                System.out.println();

                return;
            }

            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    public void stop()
    {
        FLAG = false;
    }

}


public class ProdConsumer_BlockQueueDemo
{
    public static void main(String[] args) {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{

            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动" );

            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"Prod").start();


        new Thread(()->{

            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动" );

            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"Consumer").start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( "五秒钟后结束 !" );

        myResource.stop();

    }



}
