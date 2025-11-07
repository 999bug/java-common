package com.ncst.reflact.demo2;

/**
 * 使用 cglib 动态代理对某个方法执行前后进行增强，不支持方法嵌套
 */
public class BeforeAfterDemo {
    
    public static void main(String[] args) {
        // 注意：在 Java 17+ 中运行时需要添加 JVM 参数：
        // --add-opens java.base/java.lang=ALL-UNNAMED
        
        System.out.println("=== BEFORE/AFTER 方法调用跟踪演示 ===\n");
        
        // 测试1: 创建用户流程
        testCreateUser();
        
        System.out.println("\n" + "=".repeat(100) + "\n");
        
        // 测试2: 获取用户资料流程
        testGetUserProfile();
        
        System.out.println("\n" + "=".repeat(100) + "\n");
        
        // 测试3: 错误情况
        testErrorScenario();
    }
    
    private static void testCreateUser() {
        System.out.println("📋 测试1: 创建用户流程\n");
        
        // 创建代理对象
        UserService userService = BeforeAfterProxyFactory.createBeforeAfterProxy(UserService.class);
        
        // 调用创建用户方法
        System.out.println("🚀 调用 createUser 方法:");
        UserService.User user = userService.createUser("john_doe", "john@example.com", 25);
        System.out.println("\n🎉 最终结果: " + user);
    }
    
    private static void testGetUserProfile() {
        System.out.println("📋 测试2: 获取用户资料流程\n");
        
        // 创建代理对象
        UserService userService = BeforeAfterProxyFactory.createBeforeAfterProxy(UserService.class);
        
        // 调用获取用户资料方法
        System.out.println("🚀 调用 getUserProfile 方法:");
        UserService.UserProfile profile = userService.getUserProfile("USER_123");
        System.out.println("\n🎉 最终结果: " + profile);
    }
    
    private static void testErrorScenario() {
        System.out.println("📋 测试3: 错误情况演示\n");
        
        // 创建代理对象
        UserService userService = BeforeAfterProxyFactory.createBeforeAfterProxy(UserService.class);
        
        // 测试错误情况 - 无效的用户名
        System.out.println("🚀 调用 createUser 方法（无效用户名）:");
        try {
            userService.createUser("ab", "test@example.com", 25);
        } catch (Exception e) {
            System.out.println("\n💥 捕获到预期异常: " + e.getMessage());
        }
        
        System.out.println("\n🚀 调用 createUser 方法（无效年龄）:");
        try {
            userService.createUser("valid_user", "test@example.com", -5);
        } catch (Exception e) {
            System.out.println("\n💥 捕获到预期异常: " + e.getMessage());
        }
    }
}