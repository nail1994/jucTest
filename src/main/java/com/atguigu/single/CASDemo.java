package com.atguigu.single;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dwb
 * @create 2020-11-24 11:13
 *  比较 和 交换
 *   1. CAS是什么？ ==>compareAndset()
 *   比较并交换
 *
 *  */
public class CASDemo
{

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);



        //main do ting... true
        System.out.println(atomicInteger.compareAndSet(5,2020) + "\t current data : "+atomicInteger.get());






        //false 2020
        System.out.println(atomicInteger.compareAndSet(5,1024) + "\t current data : "+atomicInteger.get());


        //i ++ ；原子性  如何解决的？
        atomicInteger.getAndIncrement();




    }

}
