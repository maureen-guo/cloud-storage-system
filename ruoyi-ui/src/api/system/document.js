import request from '@/utils/request'

//create folder
export function createFolder(folder){
    return request({
        url: '/system/info/addFolder',
        method: 'post',
        data: {
            fileName: folder.fileName,  // 文件夹名称
            parentId: folder.parentId,  // 父目录 ID
          }
      })

}

//query information of the folder
export function folderInfo(parentId){
    return request({
        url: '/system/info/getFolderInfo/' + parentId,
        method: 'get'
        
    })
}

// 查询文件信息详细
export function getInfo(fileId) {
  return request({
    url: '/system/info/' + fileId,
    method: 'get'
  })
}
