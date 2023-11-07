import request from '@/utils/request'

// 查询用于保存 每天生产计划所消耗的原材料数列表
export function listItem(query) {
  return request({
    url: '/system/item/list',
    method: 'get',
    params: query
  })
}

// 查询用于保存 每天生产计划所消耗的原材料数详细
export function getItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'get'
  })
}

// 新增用于保存 每天生产计划所消耗的原材料数
export function addItem(data) {
  return request({
    url: '/system/item',
    method: 'post',
    data: data
  })
}

// 修改用于保存 每天生产计划所消耗的原材料数
export function updateItem(data) {
  return request({
    url: '/system/item',
    method: 'put',
    data: data
  })
}

// 删除用于保存 每天生产计划所消耗的原材料数
export function delItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'delete'
  })
}
