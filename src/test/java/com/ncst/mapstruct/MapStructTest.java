package com.ncst.mapstruct;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Lsy
 * @date 2022/4/12
 */
public class MapStructTest {


    @Test
    public void do2VoTest() {
        User user = new User()
                .setId(1L)
                .setUsername("zhangsan")
                .setSex(1)
                .setPassword("abc123")
                .setCreateTime(LocalDateTime.now())
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig("[{\"field1\":\"Test Field1\",\"field2\":500}]");

        UserVo userVo = UserConverter.INSTANCE.do2vo(user);

        // asset
        assertNotNull(userVo);
        assertEquals(userVo.getId(), user.getId());

        // print
        System.out.println(user);
        System.out.println(userVo);
//        User(id=1, username=zhangsan, password=abc123, sex=1, birthday=1999-09-27, createTime=2020-08-17T14:54:01.528, config=[{"field1":"Test Field1","field2":500}])
//        UserVo(id=1, username=zhangsan, password=abc123, gender=1, birthday=1999-09-27, createTime=2020-08-17 14:54:01, config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
    }

    @Test
    public void vo2DoTest() {
        UserVo.UserConfig userConfig = new UserVo.UserConfig();
        userConfig.setField1("Test Field1");
        userConfig.setField2(500);

        UserVo userVo = new UserVo()
                .setId(1L)
                .setUsername("zhangsan")
                .setGender(2)
                .setCreateTime("2020-01-18 15:32:54")
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig(Collections.singletonList(userConfig));
        User user = UserConverter.INSTANCE.vo2Do(userVo);

        // asset
        assertNotNull(userVo);
        assertEquals(userVo.getId(), user.getId());

        // print
        System.out.println(user);
        System.out.println(userVo);
    }
}
