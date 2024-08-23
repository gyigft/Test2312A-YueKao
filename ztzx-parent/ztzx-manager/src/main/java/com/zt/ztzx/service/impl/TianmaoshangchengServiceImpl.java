package com.zt.ztzx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.ztzx.domain.Param;
import com.zt.ztzx.domain.Tianmaoshangcheng;
import com.zt.ztzx.service.TianmaoshangchengService;
import com.zt.ztzx.mapper.TianmaoshangchengMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 50568
* @description 针对表【tianmaoshangcheng】的数据库操作Service实现
* @createDate 2024-08-23 10:27:38
*/
@Service
public class TianmaoshangchengServiceImpl extends ServiceImpl<TianmaoshangchengMapper, Tianmaoshangcheng>
    implements TianmaoshangchengService{

    @Resource
    TianmaoshangchengMapper tianmaoshangchengMapper;

    @Override
    public IPage<Tianmaoshangcheng> findAll(Param param) {
        Page<Tianmaoshangcheng> page = new Page<>(param.getPageNum(), param.getPageSize());
        Page<Tianmaoshangcheng> all = tianmaoshangchengMapper.findAll(page);
        return all;
    }

    @Override
    public Integer BatchDelete(Integer[] ids) {
        Integer i = tianmaoshangchengMapper.BatchDelete(ids);
        return i;
    }

    @Override
    public List<Tianmaoshangcheng> vague(Tianmaoshangcheng tianmaoshangcheng) {
        List<Tianmaoshangcheng> vague = tianmaoshangchengMapper.vague(tianmaoshangcheng);
        return vague;
    }

    @Override
    public boolean Tombstone(Integer id) {
        boolean tombstone = tianmaoshangchengMapper.Tombstone(id);
        return tombstone;
    }
}




