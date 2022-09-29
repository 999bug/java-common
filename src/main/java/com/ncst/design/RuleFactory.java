package com.ncst.design;

/**
 * @Author: Lisy
 * @Date: 2022/09/19/15:06
 * @Description:
 */
public class RuleFactory {

    private RuleFactory() {}

    public static Platform getRule(int type) {
        switch (type) {
            case 1:
                return new CopyRule();
            case 2:
                return new HaRule();
            default:
                throw new RuntimeException("unKnow type!");
        }
    }
}
