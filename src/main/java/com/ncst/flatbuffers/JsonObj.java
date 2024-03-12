package com.ncst.flatbuffers;

import java.io.Serializable;

/**
 * @Author: Lisy
 * @Date: 2024/03/11/17:57
 * @Description:
 */
public class JsonObj implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
