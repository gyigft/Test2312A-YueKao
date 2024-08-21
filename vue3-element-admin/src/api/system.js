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
    url: '/admin/system/role/saveSysRole',
    method: 'post',
    data,
  })
}

// 保存修改
export const UpdateSysRole = data => {
  return request({
    url: '/admin/system/role/updateSysRole',
    method: 'put',
    data,
  })
}
// 删除角色
export const DeleteSysRoleById = roleId => {
  return request({
    url: '/admin/system/role/deleteById/' + roleId,
    method: 'delete',
  })
}
// 查询所有的角色数据
export const GetAllRoleList = userId => {
  return request({
    url: `/admin/system/role/findAllRoles/${userId}`,
    method: 'get',
  })
}
