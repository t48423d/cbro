import request from '@/utils/request'

// 查询产品线 （产品大类）列表
export function listLine(query) {
  return request({
    url: '/system/productLine/list',
    method: 'get',
    params: query
  })
}

// 查询产品线 （产品大类）详细
export function getLine(id) {
  return request({
    url: '/system/productLine/' + id,
    method: 'get'
  })
}

// 新增产品线 （产品大类）
export function addLine(data) {
  return request({
    url: '/system/productLine',
    method: 'post',
    data: data
  })
}

// 修改产品线 （产品大类）
export function updateLine(data) {
  return request({
    url: '/system/productLine',
    method: 'put',
    data: data
  })
}

// 删除产品线 （产品大类）
export function delLine(id) {
  return request({
    url: '/system/productLine/' + id,
    method: 'delete'
  })
}
