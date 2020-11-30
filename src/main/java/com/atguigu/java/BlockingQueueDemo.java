package com.atguigu.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author dwb
 * @create 2020-11-30 21:25
 * 阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception{
        //只有3个位置
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        //阻塞两秒钟
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    }
}
