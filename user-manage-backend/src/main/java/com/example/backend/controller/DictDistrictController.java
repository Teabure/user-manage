package com.example.backend.controller;

import com.example.backend.domain.DictDistrict;
import com.example.backend.service.DictDistrictService;
import com.example.backend.vo.DistrictVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author reine
 */
@RestController
@CrossOrigin
public class DictDistrictController {

    private DictDistrictService dictDistrictService;

    public DictDistrictController(DictDistrictService dictDistrictService) {
        this.dictDistrictService = dictDistrictService;
    }

    @GetMapping("district/children/{code}")
    public List<DictDistrict> getChildren(@PathVariable("code") String code){
        return dictDistrictService.getChildren(code);
    }

    @GetMapping("district/all")
    public List<DistrictVo> getDistrict(){
        return dictDistrictService.getDistrict();
    }


}
