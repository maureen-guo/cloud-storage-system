package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFileInfoMapper;
import com.ruoyi.system.domain.SysFileInfo;
import com.ruoyi.system.service.ISysFileInfoService;

/**
 * 文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@Service
public class SysFileInfoServiceImpl implements ISysFileInfoService 
{
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    @Override
    public SysFileInfo selectSysFileInfoByFileId(Long fileId)
    {
        return sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param sysFileInfo 文件信息
     * @return 文件信息
     */
    @Override
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.selectSysFileInfoList(sysFileInfo);
    }

    /**
     * 新增文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int insertSysFileInfo(SysFileInfo sysFileInfo)
    {
        // 打印前端传递的参数
        System.out.println("准备插入数据库的文件信息：" + sysFileInfo);
        // 调用Mapper接口插入数据
        return sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
    }

    /**
     * 新增文件夹
     */
    @Override
    public int insertFolder(SysFileInfo sysFileInfo) {
        // 打印前端传递的参数
        System.out.println("新建文件夹准备插入数据库的文件信息：" + sysFileInfo);
        if(sysFileInfo.getParentId() == null) {
           sysFileInfo.setFilePath("/"+sysFileInfo.getFileName());
        }
        else{
            //获取父目录的 filePath
            long parentid = sysFileInfo.getParentId();
            SysFileInfo folderinfo = sysFileInfoMapper.selectSysFileInfoByFileId(parentid);
            if (folderinfo == null || folderinfo.getIsFolder() != 1) {
                throw new IllegalArgumentException("父目录不存在或不是文件夹");
            }
            String path = folderinfo.getFilePath()+"/"+sysFileInfo.getFileName();
            sysFileInfo.setFilePath(path);
        }
        sysFileInfo.setIsFolder(1);
        return sysFileInfoMapper.insertFolder(sysFileInfo);
    }

    /*
     *查询文件夹信息
     */
    @Override
    public List<SysFileInfo> selectSysFileInfoByParentId(Long fileId){
        return sysFileInfoMapper.selectSysFileInfoByParentId(fileId);
    }



    /**
     * 修改文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int updateSysFileInfo(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.updateSysFileInfo(sysFileInfo);
    }

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的文件信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileIds(Long[] fileIds)
    {
        return sysFileInfoMapper.deleteSysFileInfoByFileIds(fileIds);
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileId(Long fileId)
    {
        return sysFileInfoMapper.deleteSysFileInfoByFileId(fileId);
    }
}
