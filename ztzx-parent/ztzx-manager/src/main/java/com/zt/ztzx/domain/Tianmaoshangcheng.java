package com.zt.ztzx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tianmaoshangcheng
 */
@TableName(value ="tianmaoshangcheng")
@Data
public class Tianmaoshangcheng implements Serializable {
    private Integer id;

    private String typeid;

    private String name;

    private String fid;

    private Integer tdengji;

    private Integer is_deleted;

    private static final long serialVersionUID = 1L;
}