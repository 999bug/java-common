package com.ncst.design.demo3;


import com.ncst.design.demo3.model.Cabbage;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:41
 * @Description:
 */
public class CabbageFactory extends AbstractFarmProduct {

    @Override
    public FarmProduct pick() {
        return new Cabbage();
    }

    @Override
    public FarmProduct pack() {
        return new Cabbage();
    }

    @Override
    public FarmProduct process() {
        return new Cabbage();
    }

    @Override
    public FarmProduct transport() {
        return new Cabbage();
    }

}
