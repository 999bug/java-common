package com.algo.arraystring;

import org.junit.Test;

/**
 * @Author: Lisy
 * @Date: 2024/03/14/14:58
 * @Description: https://leetcode.cn/problems/largest-element-in-an-array-after-merge-operations/description/
 */
public class MaxArrayValue {
    @Test
    public void test() {
        int[] nums = new int[]{2, 3, 7, 9, 3};
        System.out.println(maxArrayValue(nums));
        nums = new int[]{5, 3, 3};
        System.out.println(maxArrayValue1(nums));

    }

    public long maxArrayValue1(int[] nums) {
        long num = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            num = nums[i] <= num ? nums[i] + num : nums[i];
        }
        return num;
    }

    public long maxArrayValue(int[] nums) {
        long num = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] <= num) {
                num = nums[i] + num;
            } else {
                num = nums[i];
            }
        }
        return num;
    }
    public long maxArrayValue3(int[] nums) {
        long sum = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            sum = nums[i] <= sum ? nums[i] + sum : nums[i];
        }
        return sum;
    }


}
