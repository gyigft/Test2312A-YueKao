import request from '@/utils/request'

// 列表 + 分页
export function PostTianMao(data) {
  return request({
    url: '/admin/system/tianmao/PostTianMao',
    method: 'post',
    data,
  })
}

//新增用户
export function PostAdd(data) {
  return request({
    url: '/admin/system/tianmao/TianMaoSave',
    method: 'post',
    data,
  })
}

// 修改用户
export function PostUpdate(data) {
  return request({
    url: '/admin/system/tianmao/TianMaoUpdate',
    method: 'post',
    data,
  })
}

// 批量删除用户
export function DeleteDelss(ids) {
  return request({
    url: '/admin/system/tianmao/BatchDelete',
    method: 'delete',
    data: ids,
  })
}

//模糊查询
export function GetVague(data) {
  return request({
    url: '/admin/system/tianmao/TianMaoVague',
    method: 'post',
    data,
  })
}

//逻辑删除  TianMaoTombstone
// export function Tombstone(id){
//     return request({
//         url: '/admin/system/tianmao/TianMaoTombstone',
//         method: 'delete',
//         data: id
//     })
// }
