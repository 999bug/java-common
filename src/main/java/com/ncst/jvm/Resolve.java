package com.ncst.jvm;

/**
 * @author Lsy
 * @date 2022/4/27
 */
public class Resolve {

    interface Inte0 {
        int A = 0;
    }

    interface Inte1 extends Inte0 {
        int A = 1;
    }

    interface  Inte2 {
        int A = 2;
    }

    static class Parent implements Inte1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Inte2 {
        // 如果注释此此段，则会拒绝编译，因为父类和接口中均存在 A
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
