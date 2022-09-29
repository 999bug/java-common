package com.ncst.design;

/**
 * @Author: Lisy
 * @Date: 2022/09/19/15:05
 * @Description:
 */
public class DemoDesign {
    public static void main(String[] args) {
        Platform platform = RuleFactory.getRule(1);
        platform.operate("3");
    }
}
