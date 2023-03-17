package com.example.backend.vo;

import lombok.Data;

import java.util.List;

/**
 * @author reine
 */
@Data
public class DistrictVo {

    private String code;

    private String name;

    private List<DistrictVo> children;

}
