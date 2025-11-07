package com.ncst.reflact;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.function.Consumer;

/**
 * 增强的 CGLIB 代理工厂
 */
public class EnhancedCglibProxyFactory {
    
    /**
     * 创建带方法计时功能的代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createTimingProxy(Class<T> targetClass) {
        return createTimingProxy(targetClass, true, 50);
    }
    
    /**
     * 创建带配置的代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createTimingProxy(Class<T> targetClass, 
                                         boolean traceNestedCalls, 
                                         long slowThresholdMs) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new AdvancedTimingInterceptor(traceNestedCalls, slowThresholdMs));
        
        return (T) enhancer.create();
    }
    
    /**
     * 创建代理对象并支持自定义回调
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxyWithCallback(Class<T> targetClass, MethodInterceptor interceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(interceptor);
        
        return (T) enhancer.create();
    }
    
    /**
     * 创建统计功能的代理
     */
    public static <T> TimedService<T> createTimedService(Class<T> targetClass) {
        MethodStatisticsInterceptor interceptor = new MethodStatisticsInterceptor();
        T proxy = createProxyWithCallback(targetClass, interceptor);
        return new TimedService<>(proxy, interceptor);
    }
    
    /**
     * 带统计信息的服务包装类
     */
    public static class TimedService<T> {
        private final T proxy;
        private final MethodStatisticsInterceptor interceptor;
        
        public TimedService(T proxy, MethodStatisticsInterceptor interceptor) {
            this.proxy = proxy;
            this.interceptor = interceptor;
        }
        
        public T getProxy() {
            return proxy;
        }
        
        public void printStatistics() {
            interceptor.printStatistics();
        }
        
        public void resetStatistics() {
            interceptor.resetStatistics();
        }
    }
}