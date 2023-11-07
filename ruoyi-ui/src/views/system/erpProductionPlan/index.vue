<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="产品线" prop="productLineCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.productLineCode"-->
<!--          placeholder="请输入产品线"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="产品型号" prop="productModelCode">
        <el-input
          v-model="queryParams.productModelCode"
          placeholder="请输入产品型号code"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="个数" prop="count">
        <el-input
          v-model="queryParams.count"
          placeholder="请输入个数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出产日期" prop="date">
        <el-date-picker clearable
                        v-model="queryParams.date"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择出产日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="出产日期">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
<!--      <el-form-item label="0 非删除 1删除" prop="isDelete">-->
<!--        <el-input-->
<!--          v-model="queryParams.isDelete"-->
<!--          placeholder="请输入0 非删除 1删除"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="创建时间" prop="addTime">
        <el-date-picker clearable
                        v-model="queryParams.addTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建人" prop="addBy">
        <el-input
          v-model="queryParams.addBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:plan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:plan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-upload"
          size="mini"
          @click="handleImportAdd"
          v-hasPermi="['system:data:export']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportShow"
          v-hasPermi="['system:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
<!--      <el-table-column label="产品线" align="center" prop="productLineCode" />-->
      <el-table-column label="产品型号code" align="center" prop="productModelCode" />
      <el-table-column label="个数" align="center" prop="count" />
      <el-table-column label="出产日期" align="center" prop="date" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="状态 1待生产 2已完成 3延期" align="center" prop="status" />-->
<!--      <el-table-column label="0 非删除 1删除" align="center" prop="isDelete" />-->
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建人" align="center" prop="addBy" />-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:plan:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:plan:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!--    导入弹窗-->
    <el-dialog :title="importTitle" :visible.sync="importOpen" width="500px" append-to-body>
      <el-form ref="importForm" :model="importForm" :rules="rules" label-width="80px">
        <el-form-item label="文件" prop="file">
          <el-upload
            ref="upload"
            :limit="1"
            accept=".xlsx, .xls"
            :headers="upload.headers"
            :action="upload.url + '?updateSupport=' + upload.updateSupport"
            :disabled="upload.isUploading"
            :on-progress="handleFileUploadProgress"
            :on-success="handleFileSuccess"
            :auto-upload="false"
            drag
          >
            <el-button type="primary" size="small">
              上传
            </el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="importSubmitForm">确 定</el-button>
        <el-button @click="importCancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="导出" :visible.sync="exportShow" width="500px" append-to-body>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleExport">确 定</el-button>
        <el-button @click="handleExportShow">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改生产计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品线" prop="productLineCode">
          <el-input v-model="form.productLineCode" placeholder="请输入产品线" />
        </el-form-item>
        <el-form-item label="产品型号code" prop="productModelCode">
          <el-input v-model="form.productModelCode" placeholder="请输入产品型号code" />
        </el-form-item>
        <el-form-item label="个数" prop="count">
          <el-input v-model="form.count" placeholder="请输入个数" />
        </el-form-item>
        <el-form-item label="出产日期" prop="date">
          <el-date-picker clearable
                          v-model="form.date"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择出产日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="0 非删除 1删除" prop="isDelete">
          <el-input v-model="form.isDelete" placeholder="请输入0 非删除 1删除" />
        </el-form-item>
        <el-form-item label="创建时间" prop="addTime">
          <el-date-picker clearable
                          v-model="form.addTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建人" prop="addBy">
          <el-input v-model="form.addBy" placeholder="请输入创建人" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/system/erpProductionPlan";
import {getToken} from "@/utils/auth";

export default {
  name: "Plan",
  data() {
    return {
      importForm: {},
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 生产计划表格数据
      planList: [],
      // 弹出层标题
      title: "",
      importTitle:"",
      // 是否显示弹出层
      open: false,
      importOpen: false,
      exportShow: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productLineCode: null,
        productModelCode: null,
        count: null,
        date: null,
        status: null,
        isDelete: null,
        addTime: null,
        addBy: null,
        day: null,
        startTime: null,
        endTime: null,
      },
      dateRange: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/excel/importSchedule"
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询生产计划列表 */
    getList() {
      this.loading = true;
      //var dateRange = Array.isArray(this.dateRange) ? dateRange : [];
      // this.queryParams.startTime = this.dateRange[0];
      // this.queryParams.endTime = this.dateRange[1];
      listPlan( this.addDateRange(this.queryParams, this.dateRange) ).then(response => {
        this.planList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    importCancel(){
      this.importOpen = false;
      // this.importReset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        productLineCode: null,
        productModelCode: null,
        count: null,
        date: null,
        status: null,
        isDelete: null,
        addTime: null,
        addBy: null,
        updateTime: null,
        updateBy: null
      };
      this.resetForm("form");
    },
    importReset(){
      this.importForm = {
        fileList: []
      }
      this.fileList = [];
      this.resetForm("importForm");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产计划";
    },
    /** 新增按钮操作 */
    handleImportAdd() {
      this.importReset();
      this.importOpen = true;
      this.importTitle = "上传excel";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPlan(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePlan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlan(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 上传 */
    importSubmitForm() {
      this.$refs.upload.submit();
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除生产计划编号为"' + ids + '"的数据项？').then(function() {
        return delPlan(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.message + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    handleExportShow(){
      this.exportShow = !this.exportShow;
    },
    /** 导出按钮操作 */
    handleExport() {
      var date = new Date();
      var dateString = date.toISOString().substring(0,10).replaceAll("-" , "");
      console.log("==============" + dateString);
      this.queryParams.day = dateString;
      this.download('excelExport/daysPlan', {
        ...this.queryParams
      }, `plan_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
