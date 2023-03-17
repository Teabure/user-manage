package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
/**
 * @TableName t_user
 */
@TableName(value = "t_user")
@Data
public class User implements Serializable {

    /**
     * 逻辑主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别：
     */
    private String sex;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 省代码
     */
    private String provinceCode;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市代码
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区代码
     */
    private String areaCode;

    /**
     * 区名称
     */
    private String areaName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 逻辑删除标志：0-未删除，1-已删除
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}