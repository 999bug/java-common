package com.algo;

/**
 * @Author: Lisy
 * @Date: 2024/03/13/16:59
 * @Description: https://leetcode.cn/problems/maximum-odd-binary-number/
 */
public class Zuidaerjinzhijishu {
    public static void main(String[] args) {
        String s = maximumOddBinaryNumber("010");
        System.out.println("result = " + s);

        s = maximumOddBinaryNumber("11111111000000111000101");
        System.out.println("result = " + s);
    }

    public static String maximumOddBinaryNumber(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int index = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '1') {
                index++;
            }
        }
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < length; i++) {
            if (index <= 0 || --index <= 0) {
                sb.append(0);
            } else {
                sb.append(1);
            }
        }
        sb.append(1);
        return sb.toString();
    }

}
