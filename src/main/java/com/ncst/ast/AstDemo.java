package com.ncst.ast;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Lsy
 * @date 2022/4/12
 */
public class AstDemo {

    public static void main(String[] args) {
        int n = 1;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            n++;
            list.add(i);
        }
        System.out.println(n + list.size());
    }

}
