package com.ncst.design.demo1;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:14
 * @Description:
 */
public class Pear extends Fruit{
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
