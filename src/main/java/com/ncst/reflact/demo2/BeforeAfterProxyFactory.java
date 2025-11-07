package com.ncst.reflact.demo2;

import net.sf.cglib.proxy.Enhancer;

/**
 * CGLIB Before/After 代理工厂
 */
public class BeforeAfterProxyFactory {
    
    /**
     * 创建 Before/After 代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createBeforeAfterProxy(Class<T> targetClass) {
        return createBeforeAfterProxy(targetClass, true, true, true);
    }
    
    /**
     * 创建带配置的 Before/After 代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createBeforeAfterProxy(Class<T> targetClass, 
                                              boolean showArguments,
                                              boolean showReturnValue, 
                                              boolean showExecutionTime) {
        try {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(targetClass);
            enhancer.setCallback(new BeforeAfterInterceptor(showArguments, showReturnValue, showExecutionTime));
            
            return (T) enhancer.create();
        } catch (Exception e) {
            throw new RuntimeException("创建 CGLIB 代理失败，请添加 JVM 参数: " +
                "--add-opens java.base/java.lang=ALL-UNNAMED", e);
        }
    }
}