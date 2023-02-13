package com.ncst.design.demo3.model;

import com.ncst.design.demo3.FarmProduct;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/17:07
 * @Description:
 */
public class Pear extends FarmProduct {
    @Override
    public void pick() {
        System.out.println("采摘梨...");
    }

    @Override
    public void pack() {
        System.out.println("包装梨...");
    }

    @Override
    public void process() {
        System.out.println("加工梨...");
    }

    @Override
    public void transport() {
        System.out.println("运输梨....");
    }
}
