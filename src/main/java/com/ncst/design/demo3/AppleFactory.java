package com.ncst.design.demo3;

import com.ncst.design.demo3.model.Apple;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:41
 * @Description:
 */
public class AppleFactory extends AbstractFarmProduct {

    @Override
    public FarmProduct pick() {
        return new Apple();
    }

    @Override
    public FarmProduct pack() {
        return new Apple();
    }

    @Override
    public FarmProduct process() {
        return new Apple();
    }

    @Override
    public FarmProduct transport() {
        return new Apple();
    }

}
