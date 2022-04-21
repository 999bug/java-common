package com.ncst.reflect;

import org.junit.Test;

/**
 * @author Lsy
 * @date 2022/4/13
 */
public class ReflectTest {

    @Test
    public void test() throws ClassNotFoundException {
        final Class<?> aClass = Class.forName("Address");
        final String simpleName = aClass.getSimpleName();
        System.out.println("simpleName = " + simpleName);

    }
}
