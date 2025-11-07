package com.ncst.reflact.demo2;

/**
 * 用户服务类 - 用于演示嵌套方法调用
 */
public class UserService {
    
    public User createUser(String username, String email, int age) {
        System.out.println("开始创建用户...");
        
        // 嵌套调用多个方法
        validateUserData(username, email, age);
        String userId = generateUserId(username);
        User user = buildUserObject(userId, username, email, age);
        saveUserToDatabase(user);
        sendWelcomeEmail(user);
        
        System.out.println("用户创建完成");
        return user;
    }
    
    private void validateUserData(String username, String email, int age) {
        System.out.println("  验证用户数据...");
        
        validateUsername(username);
        validateEmail(email);
        validateAge(age);
        
        System.out.println("  用户数据验证完成");
    }
    
    private void validateUsername(String username) {
        System.out.println("    验证用户名...");
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (username.length() < 3) {
            throw new IllegalArgumentException("用户名至少3个字符");
        }
        sleep(20);
        System.out.println("    用户名验证通过");
    }
    
    private void validateEmail(String email) {
        System.out.println("    验证邮箱...");
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        sleep(15);
        System.out.println("    邮箱验证通过");
    }
    
    private void validateAge(int age) {
        System.out.println("    验证年龄...");
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年龄必须在0-150之间");
        }
        sleep(10);
        System.out.println("    年龄验证通过");
    }
    
    private String generateUserId(String username) {
        System.out.println("  生成用户ID...");
        sleep(25);
        String userId = "USER_" + username.toUpperCase() + "_" + System.currentTimeMillis();
        System.out.println("  用户ID生成完成: " + userId);
        return userId;
    }
    
    private User buildUserObject(String userId, String username, String email, int age) {
        System.out.println("  构建用户对象...");
        sleep(15);
        User user = new User(userId, username, email, age);
        System.out.println("  用户对象构建完成");
        return user;
    }
    
    private void saveUserToDatabase(User user) {
        System.out.println("  保存用户到数据库...");
        sleep(50);
        // 模拟数据库操作
        System.out.println("  用户保存到数据库完成");
    }
    
    private void sendWelcomeEmail(User user) {
        System.out.println("  发送欢迎邮件...");
        sleep(30);
        // 模拟发送邮件
        System.out.println("  欢迎邮件发送完成");
    }
    
    public UserProfile getUserProfile(String userId) {
        System.out.println("开始获取用户资料...");
        
        User user = fetchUserFromDB(userId);
        UserProfile profile = buildUserProfile(user);
        enrichProfileData(profile);
        
        System.out.println("用户资料获取完成");
        return profile;
    }
    
    private User fetchUserFromDB(String userId) {
        System.out.println("  从数据库获取用户...");
        sleep(40);
        User user = new User(userId, "test_user", "test@example.com", 25);
        System.out.println("  用户数据获取完成");
        return user;
    }
    
    private UserProfile buildUserProfile(User user) {
        System.out.println("  构建用户资料...");
        sleep(20);
        UserProfile profile = new UserProfile(user);
        System.out.println("  用户资料构建完成");
        return profile;
    }
    
    private void enrichProfileData(UserProfile profile) {
        System.out.println("  丰富资料数据...");
        
        addLoginHistory(profile);
        addPreferences(profile);
        calculateUserLevel(profile);
        
        System.out.println("  资料数据丰富完成");
    }
    
    private void addLoginHistory(UserProfile profile) {
        System.out.println("    添加登录历史...");
        sleep(15);
        System.out.println("    登录历史添加完成");
    }
    
    private void addPreferences(UserProfile profile) {
        System.out.println("    添加用户偏好...");
        sleep(10);
        System.out.println("    用户偏好添加完成");
    }
    
    private void calculateUserLevel(UserProfile profile) {
        System.out.println("    计算用户等级...");
        sleep(25);
        System.out.println("    用户等级计算完成");
    }
    
    // 辅助方法：模拟耗时操作
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // 用户类
    public static class User {
        private String userId;
        private String username;
        private String email;
        private int age;
        
        public User(String userId, String username, String email, int age) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.age = age;
        }
        
        // getters
        public String getUserId() { return userId; }
        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public int getAge() { return age; }
        
        @Override
        public String toString() {
            return String.format("User{id=%s, name=%s, email=%s, age=%d}", 
                userId, username, email, age);
        }
    }
    
    // 用户资料类
    public static class UserProfile {
        private User user;
        private String level = "BRONZE";
        
        public UserProfile(User user) {
            this.user = user;
        }
        
        public User getUser() { return user; }
        public String getLevel() { return level; }
        public void setLevel(String level) { this.level = level; }
        
        @Override
        public String toString() {
            return String.format("UserProfile{user=%s, level=%s}", user, level);
        }
    }
}