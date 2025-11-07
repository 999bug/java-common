package com.ncst.reflact;

public class CglibNestedMethodDemo {

    public static void main(String[] args) {
        System.out.println("=== 测试1: 嵌套方法调用跟踪 ===");
        testNestedMethodTracing();

        System.out.println("\n=== 测试2: 方法执行统计 ===");
        testMethodStatistics();

        System.out.println("\n=== 测试3: 自定义配置 ===");
        testCustomConfiguration();
    }

    private static void testNestedMethodTracing() {
        // 创建支持嵌套方法跟踪的代理
        OrderService orderService = EnhancedCglibProxyFactory.createTimingProxy(OrderService.class);

        System.out.println("创建订单:");
        OrderService.Order order = orderService.createOrder("ORDER_001", 199.99);
        System.out.println("创建结果: " + order);

        System.out.println("\n获取订单详情:");
        OrderService.Order details = orderService.getOrderDetails("ORDER_002");
        System.out.println("详情: " + details);
    }

    private static void testMethodStatistics() {
        // 创建带统计功能的代理
        EnhancedCglibProxyFactory.TimedService<OrderService> timedService =
                EnhancedCglibProxyFactory.createTimedService(OrderService.class);

        OrderService orderService = timedService.getProxy();

        // 执行多次方法调用
        for (int i = 1; i <= 3; i++) {
            try {
                orderService.createOrder("STAT_ORDER_" + i, 100 * i);
                orderService.getOrderDetails("STAT_ORDER_" + i);
            } catch (Exception e) {
                System.out.println("执行出错: " + e.getMessage());
            }
        }

        // 尝试一个会失败的操作
        try {
            orderService.createOrder("INVALID_ORDER", -100);
        } catch (Exception e) {
            System.out.println("预期中的错误: " + e.getMessage());
        }

        // 打印统计报告
        timedService.printStatistics();
    }

    private static void testCustomConfiguration() {
        // 创建不跟踪嵌套调用、阈值100ms的代理
        OrderService orderService = EnhancedCglibProxyFactory.createTimingProxy(
                OrderService.class,
                false,  // 不跟踪嵌套调用
                100     // 慢方法阈值100ms
        );

        System.out.println("自定义配置测试 - 只显示顶级方法耗时:");
        orderService.createOrder("CUSTOM_ORDER", 250.0);
        orderService.getOrderDetails("CUSTOM_ORDER");
    }
}