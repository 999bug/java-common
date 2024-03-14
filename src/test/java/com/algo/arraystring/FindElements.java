package com.algo.arraystring;

import java.util.HashSet;
import java.util.Set;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class FindElements {
    private Set<Integer> valSet;

    public FindElements(TreeNode root) {
        this.valSet = new HashSet<>();
        dfs(root, 0);
    }

    public boolean find(int target) {
        return valSet.contains(target);
    }

    private void dfs(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        node.val = val;
        valSet.add(val);
        dfs(node.left, val * 2 + 1);
        dfs(node.right, val * 2 + 2);
    }


    public static void main(String[] args) {
        // 示例用法
        TreeNode root = new TreeNode(-1);
        FindElements obj = new FindElements(root);
        System.out.println(obj.find(1)); // 返回 false
        System.out.println(obj.find(2)); // 返回 true
    }
}
