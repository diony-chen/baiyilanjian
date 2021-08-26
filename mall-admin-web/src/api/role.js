import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url: '/console/role/list',
    method: 'get',
    params: params
  })
}

export function createRole(data) {
  return request({
    url: '/console/role/add',
    method: 'post',
    data: data
  })
}

export function updateRole(id, data) {
  return request({
    url: '/console/role/update/' + id,
    method: 'post',
    data: data
  })
}

export function updateStatus(id, params) {
  return request({
    url: '/console/role/updateStatus/' + id,
    method: 'post',
    params: params
  })
}

export function deleteRole(data) {
  return request({
    url:'/console/role/delete',
    method:'post',
    data:data
  })
}

export function fetchAllRoleList() {
  return request({
    url: '/console/role/listAll',
    method: 'get'
  })
}

export function listMenuByRole(roleId) {
  return request({
    url: '/console/role/queryMenuList/'+roleId,
    method: 'get'
  })
}

export function listResourceByRole(roleId) {
  return request({
    url: '/console/role/queryResourceList/'+roleId,
    method: 'get'
  })
}

export function allocMenu(data) {
  return request({
    url: '/console/role/updateMenu',
    method: 'post',
    data:data
  })
}

export function allocResource(data) {
  return request({
    url: '/console/role/updateResource',
    method: 'post',
    data:data
  })
}
