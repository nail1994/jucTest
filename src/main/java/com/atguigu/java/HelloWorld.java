package com.atguigu.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author dwb
 * @create 2020-11-22 18:14
 */
public class HelloWorld
{

    public static void main(String[] args) {

        System.out.println("hello world ! ");

        ArrayList list2 = new ArrayList();

        list2.add(1);
        list2.add(1);
        list2.add(1);

        int num = 1;

        try {
            FileInputStream fis = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
