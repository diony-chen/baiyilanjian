import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url: '/console/resource/list',
    method: 'get',
    params: params
  })
}

export function createResource(data) {
  return request({
    url: '/console/resource/add',
    method: 'post',
    data: data
  })
}

export function updateResource(id, data) {
  return request({
    url: '/console/resource/update/' + id,
    method: 'post',
    data: data
  })
}

export function deleteResource(id) {
  return request({
    url: '/console/resource/delete/' + id,
    method: 'post'
  })
}

export function fetchAllResourceList() {
  return request({
    url: '/console/resource/listAll',
    method: 'get'
  })
}
