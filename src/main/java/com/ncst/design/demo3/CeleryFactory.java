package com.ncst.design.demo3;


import com.ncst.design.demo3.model.Celery;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:41
 * @Description:
 */
public class CeleryFactory extends AbstractFarmProduct {

    @Override
    public FarmProduct pick() {
        return new Celery();
    }

    @Override
    public FarmProduct pack() {
        return new Celery();
    }

    @Override
    public FarmProduct process() {
        return new Celery();
    }

    @Override
    public FarmProduct transport() {
        return new Celery();
    }
}
