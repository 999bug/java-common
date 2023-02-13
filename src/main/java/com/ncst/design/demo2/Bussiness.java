package com.ncst.design.demo2;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:49
 * @Description:
 */
public class Bussiness {

    public static void pick(FruitFactory fruitFactory) {
        fruitFactory.pick().pick();
    }


    public static void pack(FruitFactory fruitFactory) {
        fruitFactory.pack().pack();
    }

    public static void process(FruitFactory fruitFactory) {
        fruitFactory.process().process();
    }

    public static void transport(FruitFactory fruitFactory) {
        fruitFactory.transport().transport();
    }

}
