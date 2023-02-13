package com.ncst.design.demo3;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/17:06
 * @Description: 农产品基类，定义相关操作
 */
public abstract class FarmProduct {

    /**
     * 采摘
     */
    public abstract void pick();

    /**
     * 包装
     */
    public abstract void pack();

    /**
     * 加工
     */
    public abstract void process();

    /**
     * 运输
     */
    public abstract void transport();
}
