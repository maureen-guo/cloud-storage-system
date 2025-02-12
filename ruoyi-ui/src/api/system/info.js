import request from '@/utils/request'

import axios from 'axios'
import { getToken } from '@/utils/auth'
import { saveAs } from 'file-saver'
import { blobValidate } from "@/utils/ruoyi";
const baseURL = process.env.VUE_APP_BASE_API

// 查询文件信息列表
export function listInfo(query) {
  return request({
    url: '/system/info/list',
    method: 'get',
    params: query
  })
}

// 查询文件信息详细
export function getInfo(fileId) {
  return request({
    url: '/system/info/' + fileId,
    method: 'get'
  })
}

// 新增文件信息
export function addInfo(data) {
  return request({
    url: '/system/info',
    method: 'post',
    data: data
  })
}

// 新增多文件信息
export function addInfoBatch(data) {
  return request({
    url: '/system/info/addBatch',
    method: 'post',
    data: data
  })
}

// 修改文件信息
export function updateInfo(data) {
  return request({
    url: '/system/info',
    method: 'put',
    data: data
  })
}

// 删除文件信息
export function delInfo(fileId) {
  return request({
    url: '/system/info/' + fileId,
    method: 'delete'
  })
}

// 在 api 文件中定义下载请求
// export function downloadFile(resource) {
//   return axios({
//     url:  baseURL + '/common/download/resource?resource=' + encodeURIComponent(resource),
//     method: 'get',
//     responseType: 'blob',  // 返回的数据类型是 Blob
//     headers: { 'Authorization': 'Bearer ' + getToken() }  // 添加 token 验证
//   })
//   .then((response) => {
//     return response;  // 返回响应，供后续处理
//   })
//   .catch((error) => {
//     console.error('文件下载失败', error);
//     throw error;  // 抛出错误，供外部处理
//   });
// }

export function downloadFile(resource) {
  // 构建 URL，并对文件路径进行编码
  const url = baseURL + '/common/download/resource?resource=' + encodeURIComponent(resource);

  // 发起 GET 请求，获取文件数据
  return axios({
    method: 'get',
    url: url,
    responseType: 'blob',  // 确保返回的是二进制数据
    headers: { 'Authorization': 'Bearer ' + getToken() }  // 添加 token 验证（如果需要）
  }).then((res) => {
    // 验证返回的数据是否为 Blob 类型
    const isBlob = blobValidate(res.data);
    if (isBlob) {
      // 创建 Blob 对象，并调用 saveAs 方法保存文件
      const blob = new Blob([res.data]);
      const fileName = decodeURIComponent(res.headers['download-filename']);  // 从响应头获取文件名
      saveAs(blob, fileName);  // 保存文件
    } else {
      // 处理错误情况
      printErrMsg(res.data);  // 打印错误信息
    }
  }).catch((error) => {
    console.error('文件下载失败', error);
    // 你也可以在这里显示一个错误消息
  });
}
