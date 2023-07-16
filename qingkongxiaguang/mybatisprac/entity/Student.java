package com.qingkongxiaguangweb.mybatis_.entity;

import lombok.Data;
import lombok.experimental.Accessors;

//@Builder
@Data
@Accessors(chain = true)
public class Student {
    private Integer sid;
    private String name;
    private String sex;
}
