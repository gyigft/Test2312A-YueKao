package com.zt.ztzx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.ztzx.domain.Param;
import com.zt.ztzx.domain.Tianmaoshangcheng;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zt.ztzx.model.entity.system.SysRole;

import java.util.List;

/**
* @author 50568
* @description 针对表【tianmaoshangcheng】的数据库操作Service
* @createDate 2024-08-23 10:27:39
*/
public interface TianmaoshangchengService extends IService<Tianmaoshangcheng> {

    IPage<Tianmaoshangcheng> findAll(Param param);

    Integer BatchDelete(Integer[] ids);

    List<Tianmaoshangcheng> vague(Tianmaoshangcheng tianmaoshangcheng);

    boolean Tombstone(Integer id);
}
