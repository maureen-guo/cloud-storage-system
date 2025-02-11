package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysFileInfo;
import com.ruoyi.system.service.ISysFileInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@RestController
@RequestMapping("/system/info")
public class SysFileInfoController extends BaseController
{
    @Autowired
    private ISysFileInfoService sysFileInfoService;

    /**
     * 查询文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFileInfo sysFileInfo)
    {
        System.out.println("controller接收到的文件信息：" + sysFileInfo);
        startPage();
        //List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        Long parentId = sysFileInfo.getParentId();
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoByParentId(parentId);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFileInfo sysFileInfo)
    {
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        ExcelUtil<SysFileInfo> util = new ExcelUtil<SysFileInfo>(SysFileInfo.class);
        util.exportExcel(response, list, "文件信息数据");
    }

    /**
     * 获取文件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(sysFileInfoService.selectSysFileInfoByFileId(fileId));
    }

    /**
     * 新增文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFileInfo sysFileInfo)
    {
        System.out.println("controller接收到的文件信息：" + sysFileInfo);

        return toAjax(sysFileInfoService.insertSysFileInfo(sysFileInfo));
    }

    /**
     * 新增多文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping("/addBatch")
    public AjaxResult addBatch(@RequestBody List<SysFileInfo> sysFileInfoList) {

        System.out.println("controller接收到的文件信息：" + sysFileInfoList);

        // 遍历文件信息列表并逐一插入数据库
        int rows = 0;
        for(SysFileInfo file : sysFileInfoList) {
            rows += sysFileInfoService.insertSysFileInfo(file);
        }

        return toAjax(rows);
    }

    /**
     * 新增文件夹信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping("/addFolder")
    public AjaxResult addFolder(@RequestBody SysFileInfo sysFileInfo) {
        System.out.println("新增文件夹controller接收到的文件信息：" + sysFileInfo);

        if(sysFileInfo.getFileName() == null || sysFileInfo.getFileName().trim().isEmpty()) {
            // 如果文件夹名称为空或只包含空格，返回错误
            return AjaxResult.error("文件夹名称不能为空");
        }
        return toAjax(sysFileInfoService.insertFolder(sysFileInfo));

    }

    /*
     * 查询文件夹信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping("/getFolderInfo/{parentId}")
    public TableDataInfo getFolderInfo(@PathVariable("parentId") Long parentId) {
        System.out.println("查询文件夹信息controller接收到的文件信息：" + parentId);
        startPage();
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoByParentId(parentId);
        System.out.println("查询文件夹信息controller接收到的文件信息：" + list);
        return getDataTable(list);
    }

    /**
     * 修改文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFileInfo sysFileInfo)
    {
        return toAjax(sysFileInfoService.updateSysFileInfo(sysFileInfo));
    }

    /**
     * 删除文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(sysFileInfoService.deleteSysFileInfoByFileIds(fileIds));
    }
}
