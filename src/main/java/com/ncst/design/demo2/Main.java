package com.ncst.design.demo2;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:15
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Bussiness.pick(new AppleFactory());
        Bussiness.pack(new AppleFactory());
        Bussiness.process(new AppleFactory());
        Bussiness.transport(new AppleFactory());
        System.out.println("============");
        Bussiness.pick(new PearFactory());
        Bussiness.pack(new PearFactory());
        Bussiness.process(new PearFactory());
        Bussiness.transport(new PearFactory());
    }

}
