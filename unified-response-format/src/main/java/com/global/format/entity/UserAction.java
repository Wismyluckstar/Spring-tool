package com.global.format.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class UserAction implements Serializable {
    private Integer id;
    private Integer userId;
    private String eventName;
    private String eventData;
    private Date create_time;
    private Date update_time;
}