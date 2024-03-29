package com.ncst.mapstruct;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/**
 * @author SERVER
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserVo {
    private Long id;
    private String username;
    private String password;
    private Integer gender;
    private LocalDate birthday;
    private String createTime;
    private List<UserConfig> config;

    @Data
    public static class UserConfig {
        private String field1;
        private Integer field2;
    }
}
