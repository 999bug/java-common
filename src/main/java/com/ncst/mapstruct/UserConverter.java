package com.ncst.mapstruct;

import com.alibaba.fastjson.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 通过简单的注解 将DO 转换为VO 或者将VO 转换为DO
 * 实现原理：将本应该用户手写的代码，生成出来，在target 包下面可以查看生成的转换代码
 * 1、首先javac 对源代码进行分析，生成一颗抽象语法树（AST）
 * 2、在编译时调用了实现 JSR 269 API 的mapstruct 程序
 * 3、此时 mapstruct 会对程序进行处理，修改AST
 * 4、javac 使用修改后的AST 生成字节码文件
 * @author SERVER
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "gender", source = "sex")
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserVo do2vo(User var1);

    @Mapping(target = "sex", source = "gender")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    User vo2Do(UserVo var1);

    List<UserVo> do2voList(List<User> userList);

    /**
     * 多个对象转换成一个对象
     * 当多个原对象中，有相同名字的属性时，需要通过 @Mapping 注解来具体的指定， 以免出现歧义（不指定会报错）。 如上面的 description
     * 属性也可以直接从传入的参数来赋值。
     */
    @Mapping(source = "user.description", target = "description")
    @Mapping(source = "address.houseNo", target = "houseNumber")
    UserWithAddressVo userAndAddress2Vo(User user, Address address);


    default List<UserVo.UserConfig> strConfigToListUserConfig(String config) {
        return JSON.parseArray(config, UserVo.UserConfig.class);
    }

    default String listUserConfigToStrConfig(List<UserVo.UserConfig> list) {
        return JSON.toJSONString(list);
    }
}
