package com.ncst.reflact.demo1;

/**
 * 使用aspectj 动态对方法执行前后增强，支持方法嵌套
 */
public class AspectJLoadTimeWeavingDemo {

    public static void main(String[] args) {
        System.out.println("=== AspectJ 加载时织入演示 ===\n");

        // 创建普通的 UserService 实例
        UserService userService = new UserService();

        // 测试方法
        testCreateUser(userService);
    }

    private static void testCreateUser(UserService userService) {
        System.out.println("📋 测试创建用户流程\n");

        System.out.println("🚀 调用 createUser 方法:");
        UserService.User user = userService.createUser("john_doe", "john@example.com", 25);
        System.out.println("\n🎉 最终结果: " + user);
    }
}