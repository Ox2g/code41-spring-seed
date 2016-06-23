package me.code41.seed.api.dto;

import java.io.Serializable;

/**
 * Created by code41 on 2016/6/23.
 */
public class DemoDTO implements Serializable {
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
