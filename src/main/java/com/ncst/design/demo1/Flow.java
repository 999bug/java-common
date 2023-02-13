package com.ncst.design.demo1;

/**
 * @Author: Lisy
 * @Date: 2022/10/19/16:32
 * @Description:
 */
public class Flow {

    public static void saleFarmProducts(Fruit f) {
        f.pick();
        f.pack();
        f.process();
        f.transport();
    }
}
