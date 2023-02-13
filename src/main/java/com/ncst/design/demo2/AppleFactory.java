package com.ncst.design.demo2;

import com.ncst.design.demo2.model.Apple;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:41
 * @Description:
 */
public class AppleFactory implements FruitFactory{

    @Override
    public Fruit pick() {
        return  new Apple();
    }

    @Override
    public Fruit pack() {
        return  new Apple();
    }

    @Override
    public Fruit process() {
        return new Apple();
    }

    @Override
    public Fruit transport() {
        return  new Apple();
    }

}
