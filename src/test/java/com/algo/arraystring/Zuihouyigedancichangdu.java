package com.algo.arraystring;

import java.util.Objects;

/**
 * @Author: Lisy
 * @Date: 2024/03/13/17:31
 * @Description: https://leetcode.cn/problems/length-of-last-word/?envType=study-plan-v2&envId=top-interview-150
 */
public class Zuihouyigedancichangdu {
    public static void main(String[] args) {
        String s = "Hello World";
        int i = lengthOfLastWord(s);
        System.out.println("i = " + i);

        s = "   fly me   to   the moon  ";
        i = lengthOfLastWord(s);
        System.out.println("i = " + i);

        s = "luffy is still joyboy";
        i = lengthOfLastWord(s);
        System.out.println("i = " + i);
    }


    public static int lengthOfLastWord(String s) {
        String[] s1 = s.split(" ");
        String tmp = "";
        for (int i = s1.length - 1; i >= 0; i--) {
            if (!Objects.equals(s1[i], " ")) {
                tmp = s1[i];
                break;
            }
        }
        return tmp.length();
    }
}
