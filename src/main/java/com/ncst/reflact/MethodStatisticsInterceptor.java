package com.ncst.reflact;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 方法统计拦截器
 */
public class MethodStatisticsInterceptor implements MethodInterceptor {
    
    private final Map<String, MethodStats> statistics = new ConcurrentHashMap<>();
    
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 跳过 Object 类的基础方法
        if (method.getDeclaringClass().equals(Object.class)) {
            return proxy.invokeSuper(obj, args);
        }
        
        long startTime = System.currentTimeMillis();
        boolean success = true;
        
        try {
            Object result = proxy.invokeSuper(obj, args);
            return result;
        } catch (Exception e) {
            success = false;
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            recordStatistics(method.getName(), duration, success);
        }
    }
    
    private void recordStatistics(String methodName, long duration, boolean success) {
        MethodStats stats = statistics.computeIfAbsent(methodName, k -> new MethodStats());
        stats.recordCall(duration, success);
    }
    
    public void printStatistics() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                        方法执行统计报告");
        System.out.println("=".repeat(80));
        
        statistics.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue().getTotalTime(), e1.getValue().getTotalTime()))
            .forEach(entry -> {
                MethodStats stats = entry.getValue();
                System.out.printf("方法: %-25s | 调用次数: %-4d | 成功率: %5.1f%% | 平均耗时: %6.2f ms | 总耗时: %6d ms%n",
                    entry.getKey(),
                    stats.getCallCount(),
                    stats.getSuccessRate() * 100,
                    stats.getAverageTime(),
                    stats.getTotalTime());
            });
        
        System.out.println("=".repeat(80));
    }
    
    public void resetStatistics() {
        statistics.clear();
    }
    
    /**
     * 方法统计信息
     */
    private static class MethodStats {
        private final AtomicLong callCount = new AtomicLong();
        private final AtomicLong successCount = new AtomicLong();
        private final AtomicLong totalTime = new AtomicLong();
        private long minTime = Long.MAX_VALUE;
        private long maxTime = Long.MIN_VALUE;
        
        public void recordCall(long duration, boolean success) {
            callCount.incrementAndGet();
            if (success) {
                successCount.incrementAndGet();
            }
            totalTime.addAndGet(duration);
            minTime = Math.min(minTime, duration);
            maxTime = Math.max(maxTime, duration);
        }
        
        public long getCallCount() { return callCount.get(); }
        public long getSuccessCount() { return successCount.get(); }
        public long getTotalTime() { return totalTime.get(); }
        public double getSuccessRate() { 
            return callCount.get() == 0 ? 0 : (double) successCount.get() / callCount.get(); 
        }
        public double getAverageTime() { 
            return callCount.get() == 0 ? 0 : (double) totalTime.get() / callCount.get(); 
        }
        public long getMinTime() { return minTime == Long.MAX_VALUE ? 0 : minTime; }
        public long getMaxTime() { return maxTime == Long.MIN_VALUE ? 0 : maxTime; }
    }
}