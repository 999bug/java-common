package com.ncst.design.demo2;

import com.ncst.design.demo2.model.Pear;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:41
 * @Description:
 */
public class PearFactory implements FruitFactory{


    @Override
    public Fruit pick() {
        return new Pear();
    }

    @Override
    public Fruit pack() {
        return new Pear();
    }

    @Override
    public Fruit process() {
        return new Pear();
    }

    @Override
    public Fruit transport() {
        return new Pear();
    }

}
