import request from '@/utils/request'
import { getToken } from "@/utils/auth";

// 获取上传路径
export const getUrl = () => {
  return request({
    url: '/common/uploads',
    method: 'post',
    headers: { Authorization: "Bearer " + getToken() },
  })
}