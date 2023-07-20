import request from '@/utils/request'

// 查询产品型号列表
export function listModel(query) {
  return request({
    url: '/system/productModel/list',
    method: 'get',
    params: query
  })
}

// 查询产品型号详细
export function getModel(id) {
  return request({
    url: '/system/productModel/' + id,
    method: 'get'
  })
}

// 新增产品型号
export function addModel(data) {
  return request({
    url: '/system/productModel',
    method: 'post',
    data: data
  })
}

// 修改产品型号
export function updateModel(data) {
  return request({
    url: '/system/productModel',
    method: 'put',
    data: data
  })
}

// 删除产品型号
export function delModel(id) {
  return request({
    url: '/system/productModel/' + id,
    method: 'delete'
  })
}
