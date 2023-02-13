package com.ncst.design.demo2.model;

import com.ncst.design.demo2.Fruit;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:14
 * @Description:
 */
public class Apple extends Fruit {

    @Override
    public void pick() {
        System.out.println("采摘苹果...");
    }

    @Override
    public void pack() {
        System.out.println("包装苹果...");
    }

    @Override
    public void process() {
        System.out.println("加工苹果...");
    }

    @Override
    public void transport() {
        System.out.println("运输苹果....");
    }

}
