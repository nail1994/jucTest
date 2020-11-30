package com.atguigu.java;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类 读 写 清空
class MyCache
{
    private volatile Map<String, Object> map = new HashMap<>();

    //只能有一个线程可以进去 不能保证并发读写
//    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();


    public void put(String key,Object value){

        rwlock.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入---"+value);

            //模拟网络延迟
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            map.put(key,value);

            System.out.println(Thread.currentThread().getName() + "\t  写入完成----");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwlock.writeLock().unlock();
        }
       }


    public void get(String key)
    {

        rwlock.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取---");

            //模拟网络延迟
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Object result = map.get(key);

            System.out.println(Thread.currentThread().getName() + "\t  读取完成----result : "+result);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwlock.readLock().unlock();
        }

    }

    public void clearMap(){
        map.clear();
    }


}

/**
 * @author dwb
 * @create 2020-11-28 17:38
 * 多个线程同时读一个资源类 没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 如果一个线程想要去与共享资源，就不应该再有其他线程可以对该资源进行读或写
 *
 * 小总结：
 *          读读 能共存
 *          读写 不能共存
 *          写写 不能共存
 *
 *          写操作：原子 + 独占 ,整个过程必须是一个完整的统一体，中间不许被分隔，不许被打断
 *
 */
public class ReadWriteLockDemo
{

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5 ; i++) {

            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt + "");

            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5 ; i++) {

            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");

            },String.valueOf(i)).start();
        }
    }

}
