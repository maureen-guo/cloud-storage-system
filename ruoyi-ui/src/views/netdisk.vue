<template>
    <el-container>
        <el-header>
            <h4 color="white">网盘文件管理系统</h4>
        </el-header>
        <el-container style="height: 100vh;">
            <el-aside width="200px">
                <el-row class="tac">
                    <el-col >
                        <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose">
                        <el-submenu index="1">
                            <template slot="title"> 
                                <div>
                                    <span style="font-weight: bold; font-size: 18px; text-transform: uppercase; color: #2853B7;">all files</span>
                                </div>
                            </template>
                            <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>
            <el-container>
                <el-main class="main-box">
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item :to="{ path: '/netdisk'}"">全部文件</el-breadcrumb-item>
                        <el-breadcrumb-item v-for="(item, index) in currentPath" :key="item.id"  :to="{ path: '/folder/' + generatePath(index) }">
                            {{ item.name }}
                        </el-breadcrumb-item>
                    </el-breadcrumb>
                    <!-- <el-button @click="goBack">返回上一级</el-button> -->
                    <el-row :gutter="10" class="mb8">
                        <el-col :span="1.5">
                            <el-button
                            type="info"
                            plain
                            icon="el-icon-plus"
                            size="mini"
                            @click="handleAdd"
                            v-hasPermi="['system:info:add']"
                            >新增</el-button>
                        </el-col>
                        <el-col :span="1.5">
                            <el-button
                            type="info"
                            plain
                            icon="el-icon-upload el-icon--right"
                            size="mini"
                            @click="handleAddMulti"
                            v-hasPermi="['system:info:add']"
                            >批量上传</el-button>
                        </el-col>
                        <el-col :span="1.5">
                            <el-button
                            type="info"
                            plain
                            icon="el-icon-edit"
                            size="mini"
                            :disabled="single"
                            @click="handleUpdate"
                            v-hasPermi="['system:info:edit']"
                            >修改</el-button>
                        </el-col>
                        <el-col :span="1.5">
                            <el-button
                            type="info"
                            plain
                            icon="el-icon-delete"
                            size="mini"
                            :disabled="multiple"
                            @click="handleDelete"
                            v-hasPermi="['system:info:remove']"
                            >删除</el-button>
                        </el-col>
                        <el-col :span="1.5">
                            <el-button
                            type="info"
                            plain
                            icon="el-icon-download"
                            size="mini"
                            @click="handleDownload"
                            :disabled="multiple && single"
                            >下载</el-button>
                        </el-col>
                        <el-col :span="1.5">
                            <el-button
                            type="info"
                            plain
                            icon="el-icon-folder-add"
                            size="mini"
                            @click="addFolder"
                            >新建文件夹</el-button>
                        </el-col>
                    </el-row>
                    <el-table :data="filteredFiles" stripe @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="55" align="center" />
                    <el-table-column prop="fileName" label="名称">
                        <template slot-scope="scope">
                        <!-- 编辑模式 -->
                        <template v-if="scope.row.editing">
                            <el-input
                            v-model="scope.row.fileName"
                            placeholder="请输入文件夹名称"
                            size="mini"
                            @keyup.enter="saveFolder(scope.row)"
                            />
                        </template>
                        <!-- 普通模式 -->
                        <template v-else>
                            <i
                            v-if="scope.row.isFolder === 1"
                            class="el-icon-folder"
                            style="cursor: pointer; color: #409EFF;"
                            @click="handleFolderClick(scope.row)"
                        ></i>
                        <i
                            v-else
                            class="el-icon-document"
                        ></i>
                        <span
                            v-if="scope.row.isFolder === 1"
                            style="cursor: pointer; color: #409EFF;"
                            @click="handleFolderClick(scope.row)"
                        >
                            {{ scope.row.fileName }}
                        </span>
                        <span v-else>
                            {{ scope.row.fileName }}
                        </span>

                        </template>
                        </template>
                    </el-table-column>
                    <!-- 如果文件夹为空，展示提示 -->
                    <el-empty v-if="infoList.length === 0" description="此文件夹为空，快来上传文件吧！"></el-empty>

                    <!-- 操作列 -->
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <!-- 只有在编辑模式下才显示 -->
                            <el-button
                            v-if="scope.row.editing"
                            type="primary"
                            plain
                            size="mini"
                            @click="saveFolder(scope.row)"
                            >
                            保存
                            </el-button>
                            <el-button
                            v-if="scope.row.editing"
                            type="danger"
                            plain
                            size="mini"
                            @click="cancelFolder(scope.row)"
                            >
                            取消
                            </el-button>
                            <!-- 非编辑模式下显示删除按钮 -->
                            <!-- <el-button v-if="!scope.row.editing" type="info" icon="el-icon-delete" circle size="mini"></el-button> -->
                        </template>
                        </el-table-column>
                        <el-table-column label="文件大小" align="center" prop="size">
                            <template slot-scope="scope">
                            {{ formatSize(scope.row.size) }}
                            </template>
                        </el-table-column>
                    </el-table>
   
                <pagination
                    v-show="total>0"
                    :total="total"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getList"
                    style="margin: 10 auto; width: auto;"
                />


                </el-main>
                <el-footer>Footer</el-footer>
        

                     <!-- 添加或修改文件信息对话框 -->
                <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
                    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="文件名称" prop="fileName">
                        <el-input v-model="form.fileName" placeholder="请输入文件名称" />
                        </el-form-item>
                        <el-form-item label="文件路径" prop="filePath">
                        <!-- <el-input v-model="form.filePath" placeholder="请输入文件路径" /> -->
                        <el-upload
                            ref="upload"
                            :limit="1"
                            accept=".doc, .docx, .pdf, .ppt, .pptx"
                            :action="upload.url"
                            :headers="upload.headers"
                            :file-list="upload.fileList"
                            :on-progress="handleFileUploadProgress"
                            :on-success="handleFileSuccess"
                            :auto-upload="false">
                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                            <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading" @click="submitUpload">上传到服务器</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传doc/docx文件，且不超过10MB</div>
                        </el-upload>
                        </el-form-item>
                        <el-form-item label="文件大小" prop="size">
                        <el-input v-model="form.size" placeholder="请输入文件大小" disabled />
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="submitForm">确 定</el-button>
                        <el-button @click="cancel">取 消</el-button>
                    </div>
                </el-dialog>

                 <!-- 添加或修改多文件信息对话框 -->
         <el-dialog :title="title" :visible.sync="multiopen" width="500px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item v-for="(record, index) in fileRecords" :key="record.id" :label="'文件' + (index + 1)">
              <el-input v-model="record.fileName" placeholder="请输入文件名称" />
            </el-form-item>

            <el-form-item label="文件路径" prop="filePath">
                <el-upload
                    ref="upload" class="upload-demo" drag multiple
                    :limit="5"
                    :name="'files'"
                    accept=".doc, .docx"
                    :action="upload.url2"
                    :headers="upload.headers"
                    :file-list="upload.fileList"
                    :on-progress="handleFileUploadProgress"
                    :on-success="handleFileSuccesMulti"
                    :on-remove="handleFileRemove"
                    :auto-upload="false">
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>

                    <div slot="tip" class="el-upload__tip">只能上传doc/docx文件，且不超过10MB</div>
                </el-upload>
                </el-form-item>
                <el-form-item v-for="(record, index) in fileRecords" :key="record.id" :label="'文件' + (index + 1)+'大小'">
                <el-input v-model="record.size" placeholder="文件大小为……"  disable/>
                </el-form-item>

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading" @click="submitUpload">上传到服务器</el-button>
                    <el-button type="primary" @click="submitFormMulti">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                </div>
            </el-dialog>

            </el-container>
        </el-container>
    </el-container>

</template>


<script>

import { listInfo, getInfo, delInfo, addInfo, updateInfo, addInfoBatch, downloadFile } from "@/api/system/info";
import { createFolder, folderInfo } from "@/api/system/document"
import { getToken } from "@/utils/auth";

  export default {
    data() {
    return {
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 文件信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      multiopen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fileName: null,
        filePath: null,
        size:null,
        fileMimeType: null,
        parentId: null // 默认查询根目录
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 上传参数
      upload: {
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/common/upload",
        url2: process.env.VUE_APP_BASE_API + "/common/uploads",
        // 上传的文件列表
        fileList: []
},
     fileRecords: [],
     currentPath: [], // 当前路径数组，记录层级
     selectedFiles: [], //保存选中的文件
     currentFolder: null,  // 当前文件夹对象
    };
  },
  created() {
    // 判断是否有 fileId 参数
    const fileId = this.$route.params.fileId || null;
    console.log("当前路由的 fileId 参数为：", fileId);
    this.parentId = fileId; // 将路由参数赋值给 parentId

    // 读取 localStorage 里的路径
    const savedPath = localStorage.getItem("currentPath");
    if (savedPath) {
        this.currentPath = JSON.parse(savedPath);
    }
    
    // 如果是根目录，初始化路径
    if (!fileId) {
        this.currentPath = [];
    }

    console.log("组件创建时的 currentPath:", this.currentPath);
    // 如果有 fileId，则调用 getFolderContents，否则调用 getList
    if (fileId) {
        this.getFolderContents(fileId);
    } else {
        this.getList(); // 加载根目录内容
  }
  },
  computed: {
    filteredFiles() {
      const fileId = this.$route.params.fileId || null;
      // 计算属性，根据当前文件夹过滤文件列表
      if (fileId == null) {
        // 如果当前文件夹为空（表示在根目录），过滤出 parentId 为 null 的文件
        return this.infoList.filter(file => file.parentId === null);
      }
      // 子文件夹下显示 parentId 为当前文件夹 ID 的文件
      const filesInCurrentFolder = this.infoList;
        // 如果当前文件夹下没有文件，返回空数组
      if (filesInCurrentFolder.length === 0) {
        return [];  // 空文件夹，显示空数组
      }
      return filesInCurrentFolder;
    },
  },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
       /** 查询文件信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.parentId = this.parentId; // 设置查询的父目录ID
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        console.log("接口返回的数据：", response.rows); // 检查是否有 size 字段
        this.total = response.total;
        this.loading = false;
      });
    },
    goToRoot() {
        // 点击全部文件时，设置 parentId 为 null 并加载根目录
        this.parentId = null; // 根目录
        this.getList(); // 根据 parentId 获取文件列表
        this.currentPath = []; // 清空路径
        this.$router.push({ path: '/netdisk' }); // 手动触发路由跳转
    },
    goBack() {
        // if (this.queryParams.parentId) {
        //     this.currentPath.pop(); // 移除当前路径的最后一级
        //     this.queryParams.parentId = this.currentPath.length
        //     ? this.infoList.find(item => item.fileName === this.currentPath[this.currentPath.length - 1]).fileId
        //     : null; // 设置上一级目录的 ID
        //     this.getList(); // 刷新列表
        // }
        if (this.currentPath.length > 0) {
            this.currentPath.pop();
            localStorage.setItem("currentPath", JSON.stringify(this.currentPath));
        }
        this.$router.go(-1); // 返回上一级
        },
    handleSelectionChange(selection) {
        this.ids = selection.map(item => item.fileId);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
        // 将选中的文件保存到 selectedFiles 中
        this.selectedFiles = selection
        console.log("选中的数据：", selection)
            },
    // 取消按钮
    cancel() {
      this.open = false;
      this.multiopen = false;
      this.fileRecords = []; // 清空文件记录数组
      this.reset();
    },
     // 表单重置
    reset() {
      this.form = {
        fileId: null,
        fileName: null,
        filePath: null,
        size: null,
        fileMimeType: null,
        parentId: null, // 父文件夹ID
        isFolder: 0     // 是否是文件夹（0-文件，1-文件夹）
      };
      this.resetForm("form");
    },
     /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文件信息";
    },
    handleAddMulti(){
      this.reset();
      this.multiopen = true;
      this.title = "多文件上传信息";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fileId = row.fileId || this.ids
      getInfo(fileId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文件信息";
      });
    },
     /** 导出按钮操作 */
     handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    },
     /** 提交按钮 */
     submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            const currentFolder = this.$route.params.fileId; // 假设 currentFolder 是当前选中的文件夹对象
            // this.form.isFolder = 0; // 默认文件为非文件夹
            this.form.parentId = currentFolder ? Number(currentFolder) : null; // 如果有文件夹，则设置 parentId 为该文件夹的 ID，否则为 null
          if (this.form.fileId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // 文件删除处理
    handleFileRemove(file, fileList) {
      // 根据文件名移除对应记录
      this.fileRecords = this.fileRecords.filter(record => record.fileName !== file.name);
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const fileIds = row.fileId || this.ids;
      this.$modal.confirm('是否确认删除文件信息编号为"' + fileIds + '"的数据项？').then(function() {
        return delInfo(fileIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 文件提交处理
    submitUpload() {
      this.$refs.upload.submit();
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      console.log("上传成功响应：", response);
      this.upload.isUploading = false;
      this.form.filePath = response.url;
      this.form.size = response.size;
      this.form.fileMimeType = response.mimeType;
      // 更新表格数据
      const index = this.infoList.findIndex(item => item.fileId === this.form.fileId);
      if (index !== -1) {
        this.infoList[index].fileSize = response.size; // 更新 infoList 中的 fileSize
        this.infoList[index].fileMimeType = response.mimeType;
      }
      this.$nextTick(() => {
        console.log("表格数据更新完成：", this.infoList);
      });

      this.msgSuccess(response.msg);
},
    handleFileSuccesMulti(response, file) {
        console.log("上传成功响应：", response);
        console.log("文件对象：", file);

        const filePath = response.urls; // 修改为正确的字段名称
        if (!filePath) {
            console.error("上传响应中缺少文件路径，无法更新 fileRecords");
            return;
        }
        const record = {
            id: this.fileRecords.length + 1, // 动态生成唯一 ID
            fileName: file.name, // 文件名称
            filePath: filePath, // 文件路径
            size: response.sizes,
            fileMimeType: response.mimetypes
        };

        // 更新 fileRecords
        this.fileRecords = [...this.fileRecords, record]; // 确保响应式更新
        console.log("当前 fileRecords：", this.fileRecords);


        // 更新表单数据
        this.form.fileName = file.name;
        this.form.filePath = response.urls;
        this.form.fileRecords = this.fileRecords;
        this.form.size = response.sizes;
        this.form.fileMimeType = response.mimetypes;
        console.log("表单数据更新：", this.form);

        this.msgSuccess(`${file.name} 上传成功`);
        },
    /** 提交按钮 */
    submitFormMulti() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            // 新增操作
            addInfoBatch(this.form.fileRecords).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
        }
      });
    },
    formatSize(size) {
      if (size == 0) return `-`;
      if (size < 1024) return `${size} 字节`;
      if (size < 1024 * 1024) return `${(size / 1024).toFixed(2)} KB`;
      return `${(size / (1024 * 1024)).toFixed(2)} MB`;
    },
    handleFolderClick(folder) {
         // 处理点击文件夹的逻辑
        this.currentFolder = folder.fileId; // 将当前选中的文件夹设为 currentFolder
        console.log("当前文件夹对象：", folder);
        console.log("当前文件夹ID：", this.currentFolder);
        if (folder.isFolder === 1) { // 如果是文件夹
            this.$router.push({ 
            path: `/folder/${folder.fileId}`
        }).then(() => {
                this.currentPath.push({ name: folder.fileName, id: folder.fileId });
                console.log("父文件夹id为：", folder.fileId);
                // 存储路径到 localStorage
                localStorage.setItem("currentPath", JSON.stringify(this.currentPath));

                console.log("点击文件夹后更新 currentPath:", this.currentPath);
                
                this.getFolderContents(folder.fileId); // 调用新的接口获取文件夹内容
            }).catch(err => {
            console.error("导航失败：", err);
        });
        } else {
            this.$modal.msgWarning("这是一个文件，无法打开！");
        }
  },
  getFolderContents(parentId) {
      this.loading = true;
      console.log("当前文件夹ID：", parentId);
      folderInfo(parentId) // 调用前端的接口函数
        .then(response => {
          //this.infoList = response.data; // 更新文件夹内容
        if (response.rows && response.rows.length > 0) {
            this.infoList = response.rows; // 文件夹下有文件，更新文件列表
            console.log("接口返回的数据：", response.rows); // 检查是否有 size 字段
        } else {
            this.infoList = []; // 文件夹为空，设置为空数组
            }
          this.loading = false;
        })
        .catch(error => {
          console.error('获取文件夹内容失败', error);
          this.loading = false;
        });
    },
    addFolder(){
    // 在表格顶部插入一个默认的“新建文件夹”行

      const newFolder = {
        tempId: Date.now(), // 临时唯一 ID
        fileName: "新建文件夹", // 默认名称
        isFolder: 1, // 文件夹类型
        parentId: this.$route.params.fileId || null, // 当前目录的父 ID
        editing: true, // 是否正在编辑
      };
      console.log("parentid为", this.$route.params.fileId || null);
      this.infoList.unshift(newFolder); // 将新建的文件夹插入到第一行
    },

    // 保存文件夹
    saveFolder(row) {
      if (!row.fileName.trim()) {
        this.$message.error("文件夹名称不能为空！");
        return;
      }

      // 如果是新建文件夹（临时 ID 存在）
      if (row.tempId) {
        row.parentId = this.$route.params.fileId; // 设置父 ID
        console.log("新建文件夹的父 ID 为：", row.parentId);
        createFolder(row).then(() => {
          this.$message.success("文件夹保存成功！");
          row.editing = false; // 关闭编辑模式
          delete row.tempId; // 删除临时 ID
        }).catch(() => {
          this.$message.error("文件夹保存失败！");
        });
      } else {
        // 如果是已有文件夹，更新名称
        updateFolder(row).then(() => {
          this.$message.success("文件夹更新成功！");
          row.editing = false;
        }).catch(() => {
          this.$message.error("文件夹更新失败！");
        });
      }
    },

    // 取消新建文件夹
    cancelFolder(row) {
      if (row.tempId) {
        // 如果是新建文件夹，直接从列表中移除
        this.infoList.splice(this.infoList.indexOf(row), 1);
      } else {
        // 如果是已有文件夹，退出编辑模式
        row.editing = false;
      }
    },
    // 下载选中的文件
    handleDownload() {
        if (this.selectedFiles.length === 0) {
            this.$message.warning('请先选择文件');
            return;
        }

        // 如果只选择了一个文件，直接下载
        if (this.selectedFiles.length === 1) {
            const filePath = this.selectedFiles[0].filePath;  // 获取选中文件的路径
            this.downloadFile(filePath);
        } else {
        // 如果选择了多个文件，进行批量下载
            const filePaths = this.selectedFiles.map(file => file.filePath);  // 获取选中文件的路径
            this.batchDownload(filePaths);
        }
  },
   // 单个文件下载
  downloadFile(filePath) {
    console.log('请求文件路径：', filePath);
    downloadFile(filePath)
      .then(() => {
        this.$message.success("文件下载成功！");
      })
      .catch(error => {
        console.error('文件下载失败', error);
      });
  },
  generatePath(index) {
    // 根据当前路径数组生成对应的 fileId 路径
    const partialPath = this.currentPath.slice(0, index + 1);
    // console.log("当前路径为：", this.currentPath);
    // console.log("部分路径为：", partialPath);
    return partialPath.map(item => item.id).join('/');
  }


    }
  }
</script>

<style>

  .el-header, .el-footer {
    background-color: #606060;
    color: #ffffff;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #2853B7;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    /* background-color: #E9EEF3;
    color: #333; */
    /* text-align: center; */
    line-height: 100px;
    overflow: auto; /* 启用滚动条 */
    height: calc(100vh - 100px); /* 动态设置高度，减去其他区域占用的高度 */
  }
  .main-box {
    background-color: #D9D9D9; /* 设置背景为白色 */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); /* 浅色投影 */
    flex: 1; /* 确保内容区占据剩余空间 */
    }
  .pagination-container{
        min-height: 50px;
}

</style>