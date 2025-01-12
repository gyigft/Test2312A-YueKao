package com.zt.ztzx.model.entity.system;

import com.zt.ztzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "系统菜单实体类")
@Data
public class SysMenu extends BaseEntity {

	@Schema(description = "父节点id")
	private Long parentId;

	@Schema(description = "节点标题")
	private String title;

	@Schema(description = "组件名称")
	private String component;

	@Schema(description = "排序值")
	private Integer sortValue;

	@Schema(description = "状态(0:禁止,1:正常)")
	private Integer status;

	// 下级列表
	//自身和自身是一个一对多关系   菜单表和菜单表是一个对多关系
	@Schema(description = "子节点")
	private List<SysMenu> children;

}