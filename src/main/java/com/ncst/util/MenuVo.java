package com.ncst.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SERVER
 */
@Data
public class MenuVo {
    private Long id;
    private Long pId;
    private String name;
    private Integer rank = 0;
    private List<MenuVo> subMenus = new ArrayList<>();

    public MenuVo(long id, long pId) {
        this.id = id;
        this.pId = pId;
    }
}

