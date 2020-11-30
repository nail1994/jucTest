package com.atguigu.java;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author dwb
 * @create 2020-11-22 19:16
 */
public class TemplatesTest {

    //模板六 psf
    public static final String TEST = new String("1");

    //psfi psfs




    public static void main(String[] args) {

        //模板二
        System.out.println("hello");

        //变形soutp soutm soutv xxx.sout

        System.out.println("args = " + Arrays.deepToString(args));

        int num1 = 10;
        System.out.println("num1 = " + num1);
        System.out.println(num1);

        //fori
        String[] arr = new String[]{"1","2","3"};
        for (int i = 0; i < arr.length; i++) {


        }

        //iter
        for (String s : arr) {

        }

        //itar
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];

        }


        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        //list.for
        for (Object o : list) {
            System.out.println("o = " + o);
        }
        //list.fori
        for (int i = 0; i < list.size(); i++) {

        }

        //list.forr 倒叙遍历
        for (int i = list.size() - 1; i >= 0; i--) {

        }

        //ifn
        if (list == null) {


        }
        //变形 inn
        if (list != null) {

        }

        //xxx.nn \ xxx.null

        if (list != null) {

        }

        if (list == null) {

        }










    }



}
