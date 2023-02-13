package com.ncst.design.demo3;


import com.ncst.design.demo3.model.Pear;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:41
 * @Description:
 */
public class PearFactory extends AbstractFarmProduct {

    @Override
    public FarmProduct pick() {
        return new Pear();
    }

    @Override
    public FarmProduct pack() {
        return new Pear();
    }

    @Override
    public FarmProduct process() {
        return new Pear();
    }

    @Override
    public FarmProduct transport() {
        return new Pear();
    }

}
