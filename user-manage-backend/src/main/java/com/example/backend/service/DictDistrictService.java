package com.example.backend.service;

import com.example.backend.domain.DictDistrict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.vo.DistrictVo;

import java.util.List;

/**
* @author reine
* @description 针对表【t_dict_district】的数据库操作Service
* @createDate 2023-03-13 12:25:15
*/
public interface DictDistrictService extends IService<DictDistrict> {

    /**
     * 获取该区域下的子区域列表
     *
     * @param code
     * @return
     */
    List<DictDistrict> getChildren(String code);

    /**
     * 获取全部区域名称及邮政编码
     *
     * @return
     */
    List<DistrictVo> getDistrict();

}
