package com.algo;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Lisy
 * @Date: 2024/03/11/15:51
 * @Description: https://leetcode.cn/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
 */
public class Hebingshuzu {
    @Test
    public void test1() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        int m = 3;
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    @Test
    public void test2() {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{};
        int m = 1;
        int n = 0;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    @Test
    public void test3() {
        int[] nums1 = new int[]{0};
        int[] nums2 = new int[]{1};
        int m = 0;
        int n = 1;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    @Test
    public void test4() {
        int[] nums1 = new int[]{2, 0};
        int[] nums2 = new int[]{1};
        int m = 1;
        int n = 1;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    @Test
    public void test5() {
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 0};
        int[] nums2 = new int[]{6};
        int m = 5;
        int n = 1;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int sum = m + n - 1;
        int a = m - 1;
        int b = n - 1;
        while (b >= 0) {
            if (a >= 0 && nums1[a] > nums2[b]) {
                nums1[sum--] = nums1[a--];
            } else {
                nums1[sum--] = nums2[b--];
            }
        }
    }
}
