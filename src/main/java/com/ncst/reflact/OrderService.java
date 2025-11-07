package com.ncst.reflact;

/**
 * 包含嵌套方法调用的业务类
 */
public class OrderService {
    
    public Order createOrder(String orderId, double amount) {
        System.out.println("开始创建订单: " + orderId);
        
        // 嵌套方法调用1
        validateOrder(amount);
        
        // 嵌套方法调用2
        String orderNumber = generateOrderNumber();
        
        // 模拟业务处理
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Order order = new Order(orderId, orderNumber, amount);
        
        // 嵌套方法调用3
        saveToDatabase(order);
        
        System.out.println("订单创建完成: " + orderId);
        return order;
    }
    
    private void validateOrder(double amount) {
        System.out.println("  验证订单金额: " + amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("订单金额必须大于0");
        }
        // 模拟验证耗时
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private String generateOrderNumber() {
        System.out.println("  生成订单号");
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ORD_" + System.currentTimeMillis();
    }
    
    private void saveToDatabase(Order order) {
        System.out.println("  保存订单到数据库: " + order.getOrderNumber());
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public Order getOrderDetails(String orderId) {
        System.out.println("获取订单详情: " + orderId);
        
        // 嵌套调用其他方法
        validateOrderId(orderId);
        Order order = fetchFromDatabase(orderId);
        
        // 嵌套调用公共方法
        enrichOrderInfo(order);
        
        return order;
    }
    
    private void validateOrderId(String orderId) {
        System.out.println("  验证订单ID格式");
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("订单ID不能为空");
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private Order fetchFromDatabase(String orderId) {
        System.out.println("  从数据库获取订单");
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order(orderId, "ORD_123", 100.0);
    }
    
    private void enrichOrderInfo(Order order) {
        System.out.println("  丰富订单信息");
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        order.setStatus("COMPLETED");
    }
    
    // 订单类
    public static class Order {
        private String orderId;
        private String orderNumber;
        private double amount;
        private String status;
        
        public Order(String orderId, String orderNumber, double amount) {
            this.orderId = orderId;
            this.orderNumber = orderNumber;
            this.amount = amount;
        }
        
        // getters and setters
        public String getOrderId() { return orderId; }
        public String getOrderNumber() { return orderNumber; }
        public double getAmount() { return amount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        @Override
        public String toString() {
            return String.format("Order{id=%s, number=%s, amount=%.2f, status=%s}", 
                orderId, orderNumber, amount, status);
        }
    }
}