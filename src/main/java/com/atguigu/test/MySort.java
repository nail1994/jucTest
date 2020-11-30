package com.atguigu.test;

/**
 * @author dwb
 * @create 2020-11-23 21:58
 */
public class MySort {

    public void mySort() {
        int x = 11; //1
        int y = 12; //2
        x = x +5;   //3
        y = x *x ;  //4
    }
    //正常时顺序时1234
    //2134 多线程下可能
    //1324 也可以

    //4可以重排 变成第一吗？
    //不行，因为数据有依赖性。



}
