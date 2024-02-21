package com.ncst.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @Author: lisy
 * @Date: 2024/02/21/19:33
 * @Description:
 */
public class JnaTest {
    public interface MathDll extends Library {

        MathDll INSTANCE = Native.load("cal", MathDll.class);

        int addInt(int a, int b);

        int subInt(int a, int b);
    }


    public static void main(String[] args) {
        System.out.println("java输出返回值：" + MathDll.INSTANCE.addInt(3, 4));
    }
}
