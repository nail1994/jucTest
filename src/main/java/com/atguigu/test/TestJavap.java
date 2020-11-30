package com.atguigu.test;

/**
 * @author dwb
 * @create 2020-11-23 21:32
 * 用来试试javap 看底层字节码
 */
public class TestJavap {

    volatile int n = 0;

    public void add(){
        n++;
    }

}
