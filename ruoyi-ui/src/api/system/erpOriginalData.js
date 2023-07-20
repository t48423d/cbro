import request from '@/utils/request'

// 查询原材料信息列表
export function listData(query) {
  return request({
    url: '/system/data/list',
    method: 'get',
    params: query
  })
}

// 查询原材料信息详细
export function getData(id) {
  return request({
    url: '/system/data/' + id,
    method: 'get'
  })
}

// 新增原材料信息
export function addData(data) {
  return request({
    url: '/system/data',
    method: 'post',
    data: data
  })
}

// 修改原材料信息
export function updateData(data) {
  return request({
    url: '/system/data',
    method: 'put',
    data: data
  })
}

// 删除原材料信息
export function delData(id) {
  return request({
    url: '/system/data/' + id,
    method: 'delete'
  })
}
