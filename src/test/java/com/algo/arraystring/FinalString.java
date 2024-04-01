package com.algo.arraystring;

import org.junit.Test;

/**
 * @Author: Lisy
 * @Date: 2024/04/01/17:04
 * @Description: https://leetcode.cn/problems/faulty-keyboard/description/
 */
public class FinalString {
    @Test
    public void test() {
        System.out.println("result " + finalString("poiinter"));
        System.out.println("result " + finalString("string"));
    }

    public String finalString(String s) {
        char[] chars = s.toCharArray();
        StringBuilder tmpS = new StringBuilder();
        for (char tmp : chars) {
            if (tmp == 'i') {
                tmpS.reverse();
            } else {
                tmpS.append(tmp);
            }
        }
        return tmpS.toString();
    }

    private StringBuilder reverse(String s) {
        int num = s.length() - 1;
        int index = 0;
        StringBuilder tmp = new StringBuilder();
        while (index++ < s.length()) {
            tmp.append(s.charAt(num--));
        }
        return tmp;
    }

    private StringBuilder reverse1(String s) {
        StringBuilder tmp = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            tmp.append(chars[i]);
        }
        return tmp;
    }



}
