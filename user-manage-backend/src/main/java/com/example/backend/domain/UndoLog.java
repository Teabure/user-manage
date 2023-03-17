package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName t_undo_log
 */
@TableName(value ="t_undo_log",autoResultMap = true)
@Data
public class UndoLog implements Serializable {
    /**
     * 唯一标识，自增长
     */
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 操作人员的用户主机地址
     */
    private String userHost;

    /**
     * 被操作的数据行 ID
     */
    private Long rowId;

    /**
     * 操作类型 1插入 2更新 3删除
     */
    private Integer action;

    /**
     * 被操作的数据内容
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object user;

    /**
     * 操作时间
     */
    private Object createTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}