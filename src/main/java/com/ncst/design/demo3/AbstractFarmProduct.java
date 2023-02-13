package com.ncst.design.demo3;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/17:06
 * @Description: 农产品抽象工厂
 */
public abstract class AbstractFarmProduct {


    /**
     * 采摘
     */
    public abstract FarmProduct pick();

    /**
     * 包装
     */
    public abstract FarmProduct pack();

    /**
     * 加工
     */
    public abstract FarmProduct process();

    /**
     * 运输
     */
    public abstract FarmProduct transport();
}
