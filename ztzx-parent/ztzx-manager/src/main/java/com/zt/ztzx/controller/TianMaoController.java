package com.zt.ztzx.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.ztzx.domain.Param;
import com.zt.ztzx.domain.Tianmaoshangcheng;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.service.TianmaoshangchengService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @Author 王韬博
 * @Writing 不断奔跑, 才能靠近梦想。
 * @DateTime 2024/8/19 16:26
 */
@RestController
@RequestMapping("/admin/system/tianmao")
public class TianMaoController {


    @Resource
    TianmaoshangchengService tianmaoshangchengService;

//    分页 + 列表
    @PostMapping("/PostTianMao")
    public Result TianMao(@RequestBody Param param){
        IPage<Tianmaoshangcheng> all = tianmaoshangchengService.findAll(param);
        return Result.build(all,200,"查询成功");
    }


    //    新增用户
    @PostMapping("/TianMaoSave")
    public Result save(@RequestBody Tianmaoshangcheng tianmaoshangcheng) {
        boolean save = tianmaoshangchengService.save(tianmaoshangcheng);
        if (save) {
            return Result.build(save, 200, "添加成功");
        }
        return Result.build(save, 4000, "添加失败");
    }

    //    批量删除
    @DeleteMapping("/BatchDelete")
    public Result delete(@RequestBody Integer[] ids) {
        Integer dels = tianmaoshangchengService.BatchDelete(ids);
        return Result.build(dels, 200, "删除成功");
    }


    //    修改
    @PostMapping("/TianMaoUpdate")
    public Result update(@RequestBody Tianmaoshangcheng tianmaoshangcheng) {
        boolean update = tianmaoshangchengService.updateById(tianmaoshangcheng);
        if (update) {
            return Result.build(update, 200, "修改成功");
        }
        return Result.build(update, 4000, "修改失败");
    }

    //    模糊查询
    @PostMapping("/TianMaoVague")
    public Result vague(@RequestBody Tianmaoshangcheng tianmaoshangcheng) {
        List<Tianmaoshangcheng> vague = tianmaoshangchengService.vague(tianmaoshangcheng);
        if (vague != null) {
            return Result.build(vague, 200, "查询成功");
        } else {
            return Result.build(vague, 4000, "查询失败");
        }
    }


//    逻辑删除
    @DeleteMapping("/TianMaoTombstone")
    public Result Tombstone(Integer id) {
        boolean tombstone = tianmaoshangchengService.Tombstone(id);
        if (tombstone) {
            return Result.build(tombstone, 200, "删除成功");
        }
        return Result.build(tombstone, 4000, "删除失败");
    }


}
