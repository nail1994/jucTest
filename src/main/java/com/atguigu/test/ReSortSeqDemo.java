package com.atguigu.test;

/**
 * @author dwb
 * @create 2020-11-23 22:10
 */
public class ReSortSeqDemo {

    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;   //语句1
        flag = true; //语句2
    }

    public void method02() {
        if (flag) {
            a = a + 5; //语句3
            System.out.println("a = " + a);
        }
    }
    //可能打印出来时 5 也可能是6
}
