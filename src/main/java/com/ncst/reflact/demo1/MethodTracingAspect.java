package com.ncst.reflact.demo1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.Stack;

/**
 * AspectJ 方法跟踪切面 - 实现真正的嵌套方法拦截
 */
@Aspect
public class MethodTracingAspect {
    
    // 使用 ThreadLocal 来跟踪每个线程的调用栈
    private static final ThreadLocal<Stack<MethodCall>> callStack = ThreadLocal.withInitial(Stack::new);
    
    /**
     * 定义切点：拦截 UserService 类中的所有方法
     */
    @Pointcut("execution(* com.ncst.reflact.demo1.UserService.*(..))")
    public void userServiceMethods() {}
    
    /**
     * 定义切点：拦截 UserService 类中的私有方法
     */
    @Pointcut("execution(private * com.ncst.reflact.demo1.UserService.*(..))")
    public void userServicePrivateMethods() {}
    
    /**
     * 组合切点：拦截所有需要跟踪的方法
     */
    @Pointcut("userServiceMethods() || userServicePrivateMethods()")
    public void allTrackedMethods() {}
    
    /**
     * 环绕通知：在每个方法执行前后添加跟踪逻辑
     */
    @Around("allTrackedMethods()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名和信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringType().getSimpleName() + "." + signature.getName();
        Object[] args = joinPoint.getArgs();
        
        // 管理调用栈
        Stack<MethodCall> stack = callStack.get();
        MethodCall currentCall = new MethodCall(methodName, System.currentTimeMillis());
        stack.push(currentCall);
        
        int callDepth = stack.size() - 1;
        String indent = getIndent(callDepth);
        
        // 打印 BEFORE 信息
        printBefore(methodName, args, indent);
        
        long startTime = System.currentTimeMillis();
        Object result = null;
        boolean success = false;
        
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            success = true;
            return result;
            
        } catch (Exception e) {
            // 打印异常信息
            printException(methodName, e, indent);
            throw e;
            
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            // 打印 AFTER 信息
            printAfter(methodName, result, duration, success, indent);
            
            // 清理调用栈
            stack.pop();
            if (stack.isEmpty()) {
                callStack.remove();
            }
        }
    }
    
    /**
     * 打印方法开始信息
     */
    private void printBefore(String methodName, Object[] args, String indent) {
        System.out.printf("%s┌── BEFORE: %s", indent, methodName);
        
        if (args != null && args.length > 0) {
            System.out.printf(" - 参数: %s", formatArguments(args));
        }
        
        System.out.println();
    }
    
    /**
     * 打印方法结束信息
     */
    private void printAfter(String methodName, Object result, long duration, boolean success, String indent) {
        String status = success ? "成功" : "失败";
        
        System.out.printf("%s└── AFTER: %s - %s [%dms]", indent, methodName, status, duration);
        
        if (success && result != null) {
            System.out.printf(" - 返回值: %s", formatReturnValue(result));
        }
        
        System.out.println();
    }
    
    /**
     * 打印异常信息
     */
    private void printException(String methodName, Exception e, String indent) {
        System.out.printf("%s❌ EXCEPTION in %s: %s%n", indent, methodName, e.getMessage());
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
        return "    ".repeat(depth - 1) + "├── ";
    }
    
    /**
     * 方法调用信息类
     */
    private static class MethodCall {
        private final String methodName;
        private final long startTime;
        
        public MethodCall(String methodName, long startTime) {
            this.methodName = methodName;
            this.startTime = startTime;
        }
        
        public String getMethodName() {
            return methodName;
        }
        
        public long getStartTime() {
            return startTime;
        }
    }
}