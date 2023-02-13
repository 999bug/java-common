package com.ncst.design.demo1;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:15
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Flow.saleFarmProducts(new Pear());
        System.out.println("=========");
        Flow.saleFarmProducts(new Apple());
    }

}
