import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/console/user/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo() {
  return request({
    url: '/console/user/info',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/console/user/loginOut',
    method: 'post'
  })
}

export function fetchList(params) {
  return request({
    url: '/console/user/list',
    method: 'get',
    params: params
  })
}

export function createAdmin(data) {
  return request({
    url: '/console/user/register',
    method: 'post',
    data: data
  })
}

export function updateAdmin(id, data) {
  return request({
    url: '/console/user/update/' + id,
    method: 'post',
    data: data
  })
}

export function updateStatus(id, params) {
  return request({
    url: '/admin/updateStatus/' + id,
    method: 'post',
    params: params
  })
}

export function deleteUser(userId) {
  return request({
    url: '/console/user/delete/' + userId,
    method: 'post'
  })
}

export function getRoleByAdmin(userId) {
  return request({
    url: '/console/user/queryRoleList/' + userId,
    method: 'get'
  })
}

export function allocRole(data) {
  return request({
    url: '/console/user/updateRole',
    method: 'post',
    data: data
  })
}
