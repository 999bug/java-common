package com.ncst.mapstruct;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author SERVER
 */
@Getter
@Setter
@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer sex;
    private LocalDate birthday;
    private LocalDateTime createTime;

    // 其他扩展信息，以JSON格式存储
    private String config;
    private String description;
}
  