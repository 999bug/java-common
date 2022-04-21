package com.ncst.gson;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ncst.lombok.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Lsy
 * @date 2022/4/12
 */
@Slf4j
public class GsonTest {

    @Test
    public void test() {
        Gson gson = new Gson();
        Student student = new Student();
        student.setSex(true);
        student.setAge(18);
        student.setName("张三");


        final String s = gson.toJson(student);
        log.info(s);

        final Student student1 = gson.fromJson(s, Student.class);
        log.info("{}", student1);


        final String s1 = JSON.toJSONString(student);
        log.info(s1);

        final Student student2 = JSON.parseObject(s1, Student.class);
        log.info("{}", student2);

        // output Gson 按照实体类中属性的定义输出，fastjson 无序
        // 2022-04-12 14:35:32 INFO  GsonTest:26 - {"name":"张三","age":18,"sex":true}
        //2022-04-12 14:35:32 INFO  GsonTest:29 - Student(name=张三, age=18, sex=true)
        //2022-04-12 14:35:32 INFO  GsonTest:33 - {"age":18,"name":"张三","sex":true}
        //2022-04-12 14:35:32 INFO  GsonTest:36 - Student(name=张三, age=18, sex=true)
    }
}
