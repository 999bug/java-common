package com.ncst.lombok.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * @author Lsy
 * @date 2022/4/12
 */
@Data
public class Student {

    private String name;
    private int age;
    private boolean sex;

}
