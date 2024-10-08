package com.algo.arraystring;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @Author: Lisy
 * @Date: 2024/08/06/下午1:18
 * @Description: 找到第一个不重复的字符在一个字符串内
 */
public class FindFirstNonRepeatedChar {

    @Test
    public void testFindFirstNonRepeatedChar() {
        assertEquals('b', getFirstNonRepeatedChar("abcdefghija"));
        assertEquals('h', getFirstNonRepeatedChar("hello"));
        assertEquals('J', getFirstNonRepeatedChar("Java"));
        assertEquals('i', getFirstNonRepeatedChar("simplest"));
    }

    public char getFirstNonRepeatedChar(String str) {
        if (Objects.isNull(str) || str.isEmpty()) {
            throw new IllegalArgumentException("str is null");
        }

        Set<Character> set = new HashSet<>();
        List<Character> list = new ArrayList<>();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (set.contains(c)) {
              continue;
            }
            if (list.contains(c)) {
                list.remove((Character) c);
                set.add(c);
            } else {
                list.add(c);
            }
        }
        return list.get(0);
    }
}
