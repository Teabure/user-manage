package com.example.backend.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * @author reine
 */
public enum Action {

    INS(1,"insert"), UPD(2,"update"), DEL(3,"delete"), DEL_BATCH(4,"deleteBatch");

    @EnumValue
    private Integer code;

    @JsonValue
    private String display;

    Action(Integer code, String display) {
        this.code = code;
        this.display = display;
    }

    public Integer getCode() {
        return code;
    }



    public String getDisplay() {
        return display;
    }


}
