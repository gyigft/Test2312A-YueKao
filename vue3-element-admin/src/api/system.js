import request from '@/utils/request'

//分页带条件查询角色信息
export const GetRoleListByPage = (currentPage, pageSize, queryDto) =>
  request({
    url: '/admin/system/role/findPage/' + currentPage + '/' + pageSize,
    method: 'post',
    data: queryDto,
  })

// 添加角色请求方法
export const SaveSysRole = data => {
  return request({
    url: '/admin/system/sysRole/saveSysRole',
    method: 'post',
    data,
  })
}

// 保存修改
export const UpdateSysRole = data => {
  return request({
    url: '/admin/system/sysRole/updateSysRole',
    method: 'put',
    data,
  })
}
// 删除角色
export const DeleteSysRoleById = roleId => {
  return request({
    url: '/admin/system/sysRole/deleteById/' + roleId,
    method: 'delete',
  })
}
