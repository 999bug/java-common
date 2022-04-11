package com.ncst.junit.order;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author Lsy
 * @date 2022/4/11
 */
@FixMethodOrder
public class OrderTest {

    @Test
    public void testC() {
        System.out.println("third");
    }

    @Test
    public void testA() {
        System.out.println("first");
    }

    @Test
    public void testB() {
        System.out.println("second");
    }
}
