package com.ncst.design.demo3.model;

import com.ncst.design.demo3.FarmProduct;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/17:08
 * @Description: 卷心菜
 */
public class Cabbage extends FarmProduct {
    @Override
    public void pick() {
        System.out.println("采摘卷心菜...");
    }

    @Override
    public void pack() {
        System.out.println("包装卷心菜...");
    }

    @Override
    public void process() {
        System.out.println("加工卷心菜...");
    }

    @Override
    public void transport() {
        System.out.println("运输卷心菜....");
    }
}
