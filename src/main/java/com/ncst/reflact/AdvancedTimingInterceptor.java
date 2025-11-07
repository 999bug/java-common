package com.ncst.reflact;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Stack;

/**
 * 支持嵌套方法调用跟踪的 CGLIB 拦截器
 */
public class AdvancedTimingInterceptor implements MethodInterceptor {
    
    // 使用 ThreadLocal 来跟踪调用栈
    private static final ThreadLocal<Stack<MethodCall>> callStack = ThreadLocal.withInitial(Stack::new);
    
    // 配置选项
    private final boolean traceNestedCalls;
    private final long slowThresholdMs;
    
    public AdvancedTimingInterceptor() {
        this(true, 50); // 默认跟踪嵌套调用，慢方法阈值50ms
    }
    
    public AdvancedTimingInterceptor(boolean traceNestedCalls, long slowThresholdMs) {
        this.traceNestedCalls = traceNestedCalls;
        this.slowThresholdMs = slowThresholdMs;
    }
    
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 跳过 Object 类的基础方法
        if (method.getDeclaringClass().equals(Object.class)) {
            return proxy.invokeSuper(obj, args);
        }
        
        Stack<MethodCall> stack = callStack.get();
        MethodCall currentCall = new MethodCall(method.getName(), System.currentTimeMillis());
        stack.push(currentCall);
        
        int callDepth = stack.size() - 1;
        String indent = getIndent(callDepth);
        
        try {
            // 记录方法开始
            if (traceNestedCalls) {
                System.out.printf("%s↳ 开始执行: %s%s%n", 
                    indent, method.getName(), formatArgs(args));
            }
            
            // 执行原始方法
            Object result = proxy.invokeSuper(obj, args);
            
            return result;
            
        } catch (Exception e) {
            currentCall.setFailed(true);
            throw e;
            
        } finally {
            long endTime = System.currentTimeMillis();
            currentCall.setEndTime(endTime);
            stack.pop();
            
            // 打印耗时信息
            printTimingInfo(currentCall, callDepth, indent);
            
            // 清理 ThreadLocal
            if (stack.isEmpty()) {
                callStack.remove();
            }
        }
    }
    
    private void printTimingInfo(MethodCall call, int depth, String indent) {
        long duration = call.getDuration();
        String status = call.isFailed() ? "❌ 失败" : "✅ 成功";
        
        if (traceNestedCalls) {
            System.out.printf("%s↲ 完成: %s - %d ms %s%n", 
                indent, call.getMethodName(), duration, status);
        } else if (depth == 0) { // 只打印顶级方法的耗时
            System.out.printf("方法 %s 执行完成 - %d ms %s", 
                call.getMethodName(), duration, status);
            
            if (duration > slowThresholdMs) {
                System.out.printf(" 🐢 较慢(超过%dms)", slowThresholdMs);
            }
            System.out.println();
        }
    }
    
    private String getIndent(int depth) {
        return "  ".repeat(Math.max(0, depth));
    }
    
    private String formatArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return "()";
        }
        
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) sb.append(", ");
            if (args[i] instanceof String) {
                sb.append('"').append(args[i]).append('"');
            } else {
                sb.append(String.valueOf(args[i]));
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * 方法调用信息类
     */
    private static class MethodCall {
        private final String methodName;
        private final long startTime;
        private long endTime;
        private boolean failed;
        
        public MethodCall(String methodName, long startTime) {
            this.methodName = methodName;
            this.startTime = startTime;
        }
        
        public long getDuration() {
            return endTime - startTime;
        }
        
        // getters and setters
        public String getMethodName() { return methodName; }
        public long getStartTime() { return startTime; }
        public long getEndTime() { return endTime; }
        public void setEndTime(long endTime) { this.endTime = endTime; }
        public boolean isFailed() { return failed; }
        public void setFailed(boolean failed) { this.failed = failed; }
    }
}