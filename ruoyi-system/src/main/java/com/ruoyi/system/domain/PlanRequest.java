package com.ruoyi.system.domain;


import lombok.Data;

import java.util.List;

@Data
public class PlanRequest {

    private Integer day;

    private Integer count;

    private String code;

    private List<Integer> days;
}
