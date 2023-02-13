package com.ncst.design.demo3.model;

import com.ncst.design.demo3.FarmProduct;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/17:07
 * @Description:
 */
public class Apple extends FarmProduct {
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
