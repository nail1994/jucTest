package com.atguigu.single;

/**
 * @author dwb
 * @create 2020-11-24 10:31
 * 懒汉式 饿汉式
 * 单例模式 用来测试在多线程下单例模式 会遇见的问题。
 */
public class SingleTest
{

    private static volatile SingleTest instasnce = null;

    private SingleTest() {
        System.out.println(Thread.currentThread().getName() + " : instasnce = " + instasnce);
    }

    public static /*synchronized*/ SingleTest getInstance() {
        if(instasnce == null){
            synchronized (SingleTest.class){
                if(instasnce == null){
                    instasnce = new SingleTest();
                }
            }
        }
        return instasnce;
    }


    public static void main(String[] args) {
        //main线程的知识
        /*System.out.println(SingleTest.getInstance() == SingleTest.getInstance());
        System.out.println(SingleTest.getInstance() == SingleTest.getInstance());
        System.out.println(SingleTest.getInst ance() == SingleTest.getInstance());
*/
        //并发线程后，情况发生了很大的变化

        for (int i = 1; i <= 10 ; i++) {

            new Thread(()->{
                SingleTest.getInstance();

            },String.valueOf(i)).start();

        }


    }






}
