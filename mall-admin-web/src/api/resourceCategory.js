import request from '@/utils/request'

export function listAllCate() {
  return request({
    url: '/console/resourceCategory/list',
    method: 'get'
  })
}

export function createResourceCategory(data) {
  return request({
    url: '/console/resourceCategory/add',
    method: 'post',
    data: data
  })
}

export function updateResourceCategory(id, data) {
  return request({
    url: '/console/resourceCategory/update/' + id,
    method: 'post',
    data: data
  })
}

export function deleteResourceCategory(id) {
  return request({
    url: '/console/resourceCategory/delete/' + id,
    method: 'post'
  })
}
