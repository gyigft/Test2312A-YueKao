package com.zt.ztzx.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.ztzx.domain.Tianmaoshangcheng;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 50568
* @description 针对表【tianmaoshangcheng】的数据库操作Mapper
* @createDate 2024-08-23 10:27:39
* @Entity com.zt.ztzx.domain.Tianmaoshangcheng
*/
public interface TianmaoshangchengMapper extends BaseMapper<Tianmaoshangcheng> {

    Page<Tianmaoshangcheng> findAll(Page<Tianmaoshangcheng> page);

    Integer BatchDelete(@Param("ids") Integer[] ids);

    List<Tianmaoshangcheng> vague(Tianmaoshangcheng tianmaoshangcheng);

    boolean Tombstone(@Param("id") Integer id);

}




