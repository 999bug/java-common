package com.ncst.design.demo3;


/**
 * @Author: Lisy
 * @Date: 2022/10/19/17:14
 * @Description:
 */
    public class Bussiness {

    public static void pick(AbstractFarmProduct abstractFarmProduct) {
        abstractFarmProduct.pick().pick();
    }

    public static void pack(AbstractFarmProduct abstractFarmProduct) {
        abstractFarmProduct.pack().pack();
    }

    public static void process(AbstractFarmProduct abstractFarmProduct) {
        abstractFarmProduct.process().process();
    }

    public static void transport(AbstractFarmProduct abstractFarmProduct) {
        abstractFarmProduct.transport().transport();
    }

}
