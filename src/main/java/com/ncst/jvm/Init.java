package com.ncst.jvm;

/**
 * @author Lsy
 * @date 2022/4/27
 */
public class Init {

    static class Parent {
        public static int A = 1;
        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
