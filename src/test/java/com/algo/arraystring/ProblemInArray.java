package com.algo.arraystring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lisy
 * @Date: 2024/08/05/21:22
 * @Description: 如何找到整数数组中所有和等于给定数字的对
 */
public class ProblemInArray {

    public static void main(String[] args) {
        prettyPrint(getRandomArray(9), 11);
        prettyPrint(getRandomArray(10), 12);
    }

    public static void printPairs3(int[] array, int sum) {
        for (int i = 0; i < array.length; i++) {
            int first = array[i];
            for (int j = i + 1; j < array.length; j++) {
                int second = array[j];
                if ((first + second) == sum) {
                    System.out.printf("(%d, %d) %n", first, second);
                }
            }

        }
    }

    public static void printPairs4(int[] array, int sum) {
        if (array.length < 2) {
            return;
        }
        Set<Integer> set = new HashSet<>(array.length);
        for (int num : array) {
            int target = sum - num;
            if (!set.contains(target)) {
                set.add(target);
            } else {
                System.out.printf("(%d, %d) %n", num, target);
            }
        }
    }

    public static void printPairs(int[] array, int sum) {
        if (array.length < 2) {
            return;
        }
        Arrays.sort(array);
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int l = array[end];
            int r = array[start];
            int tmp = r + l;
            if (tmp == sum) {
                System.out.printf("(%d, %d) %n", r, l);
                start++;
                end--;
            } else if (tmp < sum) {
                start++;
            } else {
                end--;
            }
        }
    }

    /**
     * Utility method to print input and output for better explanation.
     */
    public static void prettyPrint(int[] givenArray, int givenSum) {
        System.out.println("Given array : " + Arrays.toString(givenArray));
        System.out.println("Given sum : " + givenSum);
        System.out.println("Integer numbers, whose sum is equal to value : "
                + givenSum);
        printPairs(givenArray, givenSum);
    }

    /**
     * Utility method to return random array of Integers in a range of 0 to 15
     */
    public static int[] getRandomArray(int length) {
        int[] randoms = new int[length];
        for (int i = 0; i < length; i++) {
            randoms[i] = (int) (Math.random() * 15);
        }
        return randoms;
    }
}
