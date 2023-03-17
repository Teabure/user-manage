package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.DictDistrict;
import com.example.backend.service.DictDistrictService;
import com.example.backend.mapper.DictDistrictMapper;
import com.example.backend.vo.DistrictVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author reine
 * @description 针对表【t_dict_district】的数据库操作Service实现
 * @createDate 2023-03-13 12:25:15
 */
@Service
public class DictDistrictServiceImpl extends ServiceImpl<DictDistrictMapper, DictDistrict>
        implements DictDistrictService {

    @Override
    public List<DictDistrict> getChildren(String code) {
        LambdaQueryWrapper<DictDistrict> queryWrapper = Wrappers.lambdaQuery(DictDistrict.class);
        queryWrapper.eq(DictDistrict::getParent, code);
        return list(queryWrapper);
    }

    @Override
    public List<DistrictVo> getDistrict() {
        List<DictDistrict> dictDistricts = list();
        return buildDistrictTree("86", dictDistricts);
    }

    private List<DistrictVo> buildDistrictTree(String parentCode, List<DictDistrict> dictDistricts) {
        List<DistrictVo> result = new ArrayList<>();
        // 遍历所有行政区划
        for (DictDistrict district : dictDistricts) {
            // 如果该行政区划的父级 code 与当前节点的 code 相同，则将该行政区划添加到当前节点的 children 中
            if (Objects.equals(parentCode, district.getParent())) {
                DistrictVo districtVo = new DistrictVo();
                districtVo.setCode(district.getCode());
                districtVo.setName(district.getName());
                districtVo.setChildren(buildDistrictTree(district.getCode(), dictDistricts));
                result.add(districtVo);
            }
        }
        return result;
    }
}




