package com.ncst.design.demo2;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:39
 * @Description:
 */
public interface FruitFactory {

    /**
     * 采摘
     */
    Fruit pick();

    /**
     * 包装
     */
    Fruit pack();

    /**
     * 加工
     */
    Fruit process();

    /**
     * 运输
     */
    Fruit transport();

}
