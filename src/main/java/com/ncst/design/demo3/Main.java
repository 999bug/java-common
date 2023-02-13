package com.ncst.design.demo3;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/17:15
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Bussiness.pick(new CabbageFactory());
        System.out.println("=========");
        Bussiness.pick(new AppleFactory());
        System.out.println("=========");
        Bussiness.pack(new AppleFactory());
    }
}
