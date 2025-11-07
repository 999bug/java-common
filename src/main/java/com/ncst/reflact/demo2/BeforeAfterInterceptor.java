package com.ncst.reflact.demo2;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 * Before/After 方法拦截器 - 在每个方法执行前后明确打印
 */
public class BeforeAfterInterceptor implements MethodInterceptor {
    
    // 使用 ThreadLocal 跟踪调用栈
    private static final ThreadLocal<Stack<MethodCall>> callStack = ThreadLocal.withInitial(Stack::new);
    
    // 配置选项
    private final boolean showArguments;
    private final boolean showReturnValue;
    private final boolean showExecutionTime;
    
    public BeforeAfterInterceptor() {
        this(true, true, true);
    }
    
    public BeforeAfterInterceptor(boolean showArguments, boolean showReturnValue, boolean showExecutionTime) {
        this.showArguments = showArguments;
        this.showReturnValue = showReturnValue;
        this.showExecutionTime = showExecutionTime;
    }
    
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 跳过 Object 类的基础方法
        if (method.getDeclaringClass().equals(Object.class)) {
            return proxy.invokeSuper(obj, args);
        }
        
        // 获取调用栈和当前调用信息
        Stack<MethodCall> stack = callStack.get();
        MethodCall currentCall = new MethodCall(method, args);
        stack.push(currentCall);
        
        int callDepth = stack.size() - 1;
        String indent = getIndent(callDepth);
        
        // 打印 BEFORE
        printBefore(method, args, indent);
        
        long startTime = System.currentTimeMillis();
        Object result = null;
        boolean success = false;
        
        try {
            // 执行原始方法
            result = proxy.invokeSuper(obj, args);
            success = true;
            return result;
            
        } catch (Exception e) {
            // 打印异常信息
            printException(method, e, indent);
            throw e;
            
        } finally {
            long endTime = System.currentTimeMillis();
            currentCall.setEndTime(endTime);
            
            // 打印 AFTER
            printAfter(method, result, currentCall.getDuration(), success, indent);
            
            // 从调用栈弹出
            stack.pop();
            
            // 清理 ThreadLocal
            if (stack.isEmpty()) {
                callStack.remove();
            }
        }
    }
    
    /**
     * 打印方法开始信息
     */
    private void printBefore(Method method, Object[] args, String indent) {
        String methodName = getMethodSignature(method);
        
        System.out.printf("%s┌── BEFORE: %s", indent, methodName);
        
        if (showArguments && args != null && args.length > 0) {
            System.out.printf(" - 参数: %s", formatArguments(args));
        }
        
        System.out.println();
    }
    
    /**
     * 打印方法结束信息
     */
    private void printAfter(Method method, Object result, long duration, boolean success, String indent) {
        String methodName = getMethodSignature(method);
        String status = success ? "成功" : "失败";
        
        System.out.printf("%s└── AFTER: %s - %s", indent, methodName, status);
        
        if (showExecutionTime) {
            System.out.printf(" [%dms]", duration);
        }
        
        if (showReturnValue && success && result != null && !method.getReturnType().equals(void.class)) {
            System.out.printf(" - 返回值: %s", formatReturnValue(result));
        }
        
        System.out.println();
    }
    
    /**
     * 打印异常信息
     */
    private void printException(Method method, Exception e, String indent) {
        String methodName = getMethodSignature(method);
        System.out.printf("%s❌ EXCEPTION in %s: %s%n", indent, methodName, e.getMessage());
    }
    
    /**
     * 获取方法签名
     */
    private String getMethodSignature(Method method) {
        return method.getDeclaringClass().getSimpleName() + "." + method.getName();
    }
    
    /**
     * 格式化参数
     */
    private String formatArguments(Object[] args) {
        if (args == null || args.length == 0) {
            return "无参数";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) sb.append(", ");
            
            if (args[i] == null) {
                sb.append("null");
            } else if (args[i] instanceof String) {
                String str = (String) args[i];
                if (str.length() > 20) {
                    str = str.substring(0, 17) + "...";
                }
                sb.append('"').append(str).append('"');
            } else if (args[i] instanceof Number) {
                sb.append(args[i]);
            } else {
                String className = args[i].getClass().getSimpleName();
                sb.append(className);
            }
        }
        return sb.toString();
    }
    
    /**
     * 格式化返回值
     */
    private String formatReturnValue(Object result) {
        if (result == null) {
            return "null";
        }
        
        if (result instanceof String) {
            String str = (String) result;
            if (str.length() > 30) {
                str = str.substring(0, 27) + "...";
            }
            return '"' + str + '"';
        } else {
            return result.toString();
        }
    }
    
    /**
     * 获取缩进字符串
     */
    private String getIndent(int depth) {
        if (depth == 0) {
            return "";
        }
        return "│   ".repeat(depth - 1) + "├── ";
    }
    
    /**
     * 方法调用信息类
     */
    private static class MethodCall {
        private final Method method;
        private final Object[] arguments;
        private final long startTime;
        private long endTime;
        
        public MethodCall(Method method, Object[] arguments) {
            this.method = method;
            this.arguments = arguments;
            this.startTime = System.currentTimeMillis();
        }
        
        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }
        
        public long getDuration() {
            return endTime - startTime;
        }
    }
}