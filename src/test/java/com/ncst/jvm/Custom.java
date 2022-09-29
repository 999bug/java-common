package com.ncst.jvm;

import java.io.Serializable;

/**
 * @author Lsy
 * @date 2022/4/25
 */
public class Custom implements Serializable {
    private static final long serialVersionUID = 2709425275741743919L;
    static {
        System.out.println("自定义类加载器");
    }
}
