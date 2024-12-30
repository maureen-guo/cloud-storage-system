<template>
    <div class="app-container home">
      <el-row :gutter="20">
        <el-col :sm="24" :lg="24">
          <blockquote class="text-warning" style="font-size: 14px">
            领取阿里云通用云产品1888优惠券
            <br />
            <el-link
              href="https://www.aliyun.com/minisite/goods?userCode=brki8iof"
              type="primary"
              target="_blank"
              >https://www.aliyun.com/minisite/goods?userCode=brki8iof</el-link
            >
            <br />
            领取腾讯云通用云产品2860优惠券
            <br />
            <el-link
              href="https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console"
              type="primary"
              target="_blank"
              >https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console</el-link
            >
            <br />
            阿里云服务器折扣区
            <el-link href="http://aly.ruoyi.vip" type="primary" target="_blank"
              >>☛☛点我进入☚☚</el-link
            >
            &nbsp;&nbsp;&nbsp; 腾讯云服务器秒杀区
            <el-link href="http://txy.ruoyi.vip" type="primary" target="_blank"
              >>☛☛点我进入☚☚</el-link
            ><br />
            <h4 class="text-danger">
              云产品通用红包，可叠加官网常规优惠使用。(仅限新用户)
            </h4>
          </blockquote>

          <hr />
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :sm="24" :lg="12" style="padding-left: 20px">
          <h2>若依后台管理框架</h2>
          <p>
            Melodrama 是21世纪最伟大的专辑，看了很多优秀的开源项目但是发现没有合适自己的。于是利用空闲休息时间开始自己写一套后台系统。如此有了若依管理系统，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。
          </p>
          <p>
            <b>当前版本:</b> <span>v{{ version }}</span>
          </p>
          <p>
            <el-tag type="danger">&yen;免费开源</el-tag>
          </p>
          <p>
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-cloudy"
              plain
              @click="goTarget('https://gitee.com/y_project/RuoYi-Vue')"
              >访问码云</el-button
            >
            <el-button
              size="mini"
              icon="el-icon-s-home"
              plain
              @click="goTarget('http://ruoyi.vip')"
              >访问主页</el-button
            >
            <!-- <el-button
              size="mini"
              icon="el-icon-upload2"
              plain
              @click="handleUpload"
              >批量上传</el-button
            > -->
            <el-button type="primary"  @click="handleAdd">上传<i class="el-icon-upload el-icon--right"></i></el-button>
            <el-button type="primary"  @click="handleAddMulti">上传多文件<i class="el-icon-upload el-icon--right"></i></el-button>
          </p>
        </el-col>

        <el-col :sm="24" :lg="12" style="padding-left: 50px">
          <el-row>
            <el-col :span="12">
              <h2>技术选型</h2>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <h4>后端技术</h4>
              <ul>
                <li>SpringBoot</li>
                <li>Spring Security</li>
                <li>JWT</li>
                <li>MyBatis</li>
                <li>Druid</li>
                <li>Fastjson</li>
                <li>...</li>
              </ul>
            </el-col>
            <el-col :span="6">
              <h4>前端技术</h4>
              <ul>
                <li>Vue</li>
                <li>Vuex</li>
                <li>Element-ui</li>
                <li>Axios</li>
                <li>Sass</li>
                <li>Quill</li>
                <li>...</li>
              </ul>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <el-divider />
      <el-row :gutter="20">
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-card class="update-log">
            <div slot="header" class="clearfix">
              <span>联系信息</span>
            </div>
            <div class="body">
              <p>
                <i class="el-icon-s-promotion"></i> 官网：<el-link
                  href="http://www.ruoyi.vip"
                  target="_blank"
                  >http://www.ruoyi.vip</el-link
                >
              </p>
              <p>
                <i class="el-icon-user-solid"></i> QQ群： 无官方粉丝群
              </p>
              <p>
                <i class="el-icon-chat-dot-round"></i> 微信：<a
                  href="javascript:;"
                  >gj_0420</a
                >
              </p>
              <p>
                <i class="el-icon-money"></i> 支付宝：<a
                  href="javascript:;"
                  class="支付宝信息"
                  >15554178913 欢迎转账 ^_^</a
                >
              </p>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-card class="update-log">
            <div slot="header" class="clearfix">
              <span>top4 in 2024.12.19</span>
            </div>
            <el-collapse accordion>
              <el-collapse-item title="perfect places">
                <ul>
                  <li>Every night, I live and die</li>
                  <li>Feel the party to my bones</li>
                  <li>Watch the wasters blow the speakers</li>
                  <li>Spill my guts beneath the outdoor light</li>
                  <li>It's just another graceless night</li>
                  <li>Are you lost enough. Have another drink, get lost in us</li>
                  <li>This is how we get notorious</li>
                  <li>All of the things we are taking, cause we are young and we're ashamed</li>
                  <li>Send us to perfect places</li>
                  <li>All of our heroes fading, now I can't stand to be alone</li>
                </ul>
              </el-collapse-item>
              <el-collapse-item title="THE TORTURED POETS DEPARTMENT">
                <ul>
                  <li>I laughed in your face and said</li>
                  <li>You're not Dylan Thomas</li>
                  <li>I'm not Patti Smith</li>
                  <li>This ain't the Chelsea Hotel</li>
                  <li>We are modern idiots</li>
                  <li>And who's gonna hold you like me</li>
                </ul>
              </el-collapse-item>
              <el-collapse-item title="champagne problems">
                <ul>
                  <li>Your midas touch on the Chevy door</li>
                  <li>November flush and your flannel cure</li>
                  <li>This dorm was once a mad house</li>
                  <li>I made a joke "well it's made for me"</li>
                </ul>
              </el-collapse-item>
              <el-collapse-item title="All I want for Chritmas is you">
                <ul>
                  <li>I don't want a lot for Christmas</li>
                  <li>There is just one thing I need</li>
                  <li>I don't care about the presents</li>
                  <li>underneath the Christmas tree</li>
                </ul>
              </el-collapse-item>


            </el-collapse>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-card class="update-log">
            <div slot="header" class="clearfix">
              <span>捐赠支持</span>
            </div>
            <div class="body">
              <img
                src="@/assets/images/paygj.jpg"
                alt="donate"
                width="100%"
              />
              <span style="display: inline-block; height: 30px; line-height: 30px"
                >学习不易……你可以请作者吃个饭表示鼓励</span
              >
            </div>
          </el-card>
        </el-col>
      </el-row>

          <!-- 用户导入对话框 -->
        <!-- <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
        <el-upload ref="upload" :file-list="fileList":limit="5" accept=".xlsx, .xls" :headers="upload.headers" :multiple="true":action="upload.url + '?updateSupport=' + upload.updateSupport" :disabled="upload.isUploading" :on-progress="handleFileUploadProgress" :on-success="handleFileSuccess" :on-change="handleFileChange"
            :on-remove="handleFileRemove" :auto-upload="false" drag>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip text-center" slot="tip">
            <div class="el-upload__tip" slot="tip">
                <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的用户数据
            </div>
            <span>仅允许导入xls、xlsx格式文件。</span>
            <el-link type="primary" :underline="false" style="font-size: 12px; vertical-align: baseline" @click="importTemplate">下载模板</el-link>
            </div>
        </el-upload>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitFileForm">确 定</el-button>
            <el-button @click="upload.open = false">取 消</el-button>
        </div>
        </el-dialog> -->

        <!-- 批量上传功能 -->
        <!-- <el-dialog title="上传文件" :visible.sync="dialogFormVisible" width="800px">
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="文件名称" prop="fileName" >
                    <el-input v-model="form.fileName" placeholder="请输入文件名称" />
                </el-form-item>
            <el-form-item label="选取文件" :label-width="formLabelWidth " prop="filePath">
                <el-upload class="upload-demo" drag multiple
                    ref="upload"
                    :limit="5"
                    accept=".doc, .docx"
                    :action="upload.url"
                    :headers="upload.headers"
                    :file-list="upload.fileList"
                    :on-change="handleFileChange"
                    :on-progress="handleFileUploadProgress"
                    :on-success="handleFileSuccess"
                    :on-remove="handleFileRemove"
                    :auto-upload="false">
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div> -->

                    <!-- <el-button slot="trigger" size="small" type="primary">选取文件</el-button> -->
                    <!-- <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading" @click="submitUpload">上传到服务器</el-button> -->

                    <!-- <div slot="tip" class="el-upload__tip">只能上传doc/docx文件，且不超过10MB</div>
                </el-upload>
            </el-form-item>
            </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="cancel">取 消</el-button>
                    <el-button type="primary" :plain="true" @click="handleSubmit">确 定</el-button>
                </div>
        </el-dialog> -->

            <!-- 添加或修改文件信息对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item v-for="(record, index) in fileRecords" :key="record.id" :label="'文件' + (index + 1)">
              <el-input v-model="record.fileName" placeholder="请输入文件名称" />
            </el-form-item>

            <!-- <el-form-item label="文件名称" prop="fileName">
              <el-input v-model="form.fileName" placeholder="请输入文件名称" />
            </el-form-item> -->
            <el-form-item label="文件路径" prop="filePath">
              <el-upload
                ref="upload" class="upload-demo" drag multiple
                :limit="5"
                accept=".doc, .docx"
                :action="upload.url"
                :headers="upload.headers"
                :file-list="upload.fileRecords"
                :on-progress="handleFileUploadProgress"
                :on-success="handleFileSuccess"
                :on-remove="handleFileRemove"
                :auto-upload="false">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <!-- <el-button slot="trigger" size="small" type="primary">选取文件</el-button> -->

                <div slot="tip" class="el-upload__tip">只能上传doc/docx文件，且不超过10MB</div>
              </el-upload>
            </el-form-item>

          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading" @click="submitUpload">上传到服务器</el-button>
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>

         <!-- 添加或修改文件信息对话框 -->
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
                :on-success="handleFileSuccess"
                :on-remove="handleFileRemove"
                :auto-upload="false">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>

                <div slot="tip" class="el-upload__tip">只能上传doc/docx文件，且不超过10MB</div>
              </el-upload>
            </el-form-item>

          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading" @click="submitUpload">上传到服务器</el-button>
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>


    </div>
  </template>

  <script>

import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/system/info";
import { getToken } from "@/utils/auth";

export default {
  name: "Info",
  data() {
    return {
      // 版本号
      version: "3.8.8",
      // 遮罩层
      loading: true,
      // 非多个禁用
      multiple: true,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      multiopen: false,
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
        fileList: [],
      },
      fileRecords: [], // 动态生成的文件记录，包含每个文件的信息
       // 查询参数
      queryParams: {
        fileName: null,
        filePath: null
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询文件信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.$refs.upload.clearFiles(); // 清空上传组件内部的文件
      this.fileRecords = []; // 清空文件记录数组
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        fileId: null,
        fileName: null,
        filePath: null
      };
      this.resetForm("form");
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
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

    // 文件提交处理
    submitUpload() {
      this.$refs.upload.submit();
    },

    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      console.log("into handleFileUploadProgress")
      this.upload.isUploading = true;
    },
//     // 文件上传成功处理
//     handleFileSuccess(response, file, fileList) {
//       this.upload.isUploading = false;
//       this.form.filePath = response.url;
//       this.msgSuccess(response.msg);
// },
 // 文件上传成功处理
 handleFileSuccess(response, file) {
      const record = {
        id: this.fileRecords.length + 1, // 动态生成唯一 ID
        fileName: file.name, // 文件名称
        filePath: response.url, // 文件路径
      };
      this.fileRecords.push(record); // 将文件记录添加到数组
      this.upload.isUploading = false;
      // 打印调试信息
      console.log("文件上传成功：", file);
      console.log("当前 fileRecords：", this.fileRecords);

       // 更新表单的 fileName 和 filePath
      this.form.fileName = file.name;
      this.form.filePath = response.url;
      // 合并文件记录到 this.form
      this.form.fileRecords = this.fileRecords;
      console.log("表单数据更新：", this.form);

      this.msgSuccess(`${file.name} 上传成功`);
    },
    // 文件删除处理
    handleFileRemove(file, fileList) {
      // 根据文件名移除对应记录
      this.fileRecords = this.fileRecords.filter(record => record.fileName !== file.name);
    },

}
};
  </script>

  <style scoped lang="scss">
  .home {
    blockquote {
      padding: 10px 20px;
      margin: 0 0 20px;
      font-size: 17.5px;
      border-left: 5px solid #eee;
    }
    hr {
      margin-top: 20px;
      margin-bottom: 20px;
      border: 0;
      border-top: 1px solid #eee;
    }
    .col-item {
      margin-bottom: 20px;
    }

    ul {
      padding: 0;
      margin: 0;
    }

    font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 13px;
    color: #676a6c;
    overflow-x: hidden;

    ul {
      list-style-type: none;
    }

    h4 {
      margin-top: 0px;
    }

    h2 {
      margin-top: 10px;
      font-size: 26px;
      font-weight: 100;
    }

    p {
      margin-top: 10px;

      b {
        font-weight: 700;
      }
    }

    .update-log {
      ol {
        display: block;
        list-style-type: decimal;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0;
        margin-inline-end: 0;
        padding-inline-start: 40px;
      }
    }
  }
  </style>
