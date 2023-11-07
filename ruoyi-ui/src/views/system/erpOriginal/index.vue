<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号" prop="coder">
        <el-input
          v-model="queryParams.coder"
          placeholder="请输入编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="描述" prop="desc">-->
<!--        <el-input-->
<!--          v-model="queryParams.desc"-->
<!--          placeholder="请输入描述"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="供应商" prop="suppier">
        <el-input
          v-model="queryParams.suppier"
          placeholder="请输入供应商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="buyer">
        <el-input
          v-model="queryParams.buyer"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发货地址" prop="deliverySite">
        <el-input
          v-model="queryParams.deliverySite"
          placeholder="请输入发货地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否特殊" prop="special">
        <el-input
          v-model="queryParams.special"
          placeholder="输入0或1, 0是非特殊 1是特殊"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="物流运输天数" prop="transport">-->
<!--        <el-input-->
<!--          v-model="queryParams.transport"-->
<!--          placeholder="请输入物流运输天数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="库存预留天数" prop="bufferDay">-->
<!--        <el-input-->
<!--          v-model="queryParams.bufferDay"-->
<!--          placeholder="请输入库存预留天数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="raw" prop="raw">
        <el-input
          v-model="queryParams.raw"
          placeholder="请输入raw"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="rds" prop="rds">
        <el-input
          v-model="queryParams.rds"
          placeholder="请输入rds"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="wip" prop="wip">
        <el-input
          v-model="queryParams.wip"
          placeholder="请输入wip"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="库存" prop="total">-->
<!--        <el-input-->
<!--          v-model="queryParams.total"-->
<!--          placeholder="请输入库存"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="计划者coder" prop="plannerCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.plannerCode"-->
<!--          placeholder="请输入计划者coder"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="0非删除 1删除" prop="isDelete">-->
<!--        <el-input-->
<!--          v-model="queryParams.isDelete"-->
<!--          placeholder="请输入0非删除 1删除"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建时间" prop="addTime">-->
<!--        <el-date-picker clearable-->
<!--                        v-model="queryParams.addTime"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="请选择创建时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建人" prop="addBy">-->
<!--        <el-input-->
<!--          v-model="queryParams.addBy"-->
<!--          placeholder="请输入创建人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="修改人" prop="updateBu">-->
<!--        <el-input-->
<!--          v-model="queryParams.updateBu"-->
<!--          placeholder="请输入修改人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['system:data:add']"
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
          v-hasPermi="['system:data:edit']"
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
          v-hasPermi="['system:data:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['system:data:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="编号" align="center" prop="coder" />
      <el-table-column label="中文名称或者描述" align="center" prop="remark" />
      <el-table-column label="描述" align="center" prop="desc" />
      <el-table-column label="供应商" align="center" prop="suppier" />
      <el-table-column label="联系人" align="center" prop="buyer" />
      <el-table-column label="发货地址" align="center" prop="deliverySite" />
      <el-table-column label="是否特殊" align="center" prop="special" >
        <template slot-scope="scope">
          <span v-if="scope.row.special===1">特殊</span>
          <span v-else-if="scope.row.special===0">非特殊</span>
        </template>
      </el-table-column>
      <el-table-column label="物流运输天数" align="center" prop="transport" />
      <el-table-column label="库存预留天数" align="center" prop="bufferDay" />
      <el-table-column label="raw" align="center" prop="raw" />
      <el-table-column label="rds" align="center" prop="rds" />
      <el-table-column label="wip" align="center" prop="wip" />
      <el-table-column label="库存" align="center" prop="total" />
      <el-table-column label="计划者coder" align="center" prop="plannerCode" />
<!--      <el-table-column label="0非删除 1删除" align="center" prop="isDelete" />-->
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建人" align="center" prop="addBy" />-->
<!--      <el-table-column label="修改人" align="center" prop="updateBu" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:data:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:data:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
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
    <!-- 添加或修改原材料信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="编号" prop="coder">
          <el-input v-model="form.coder" placeholder="请输入编号" />
        </el-form-item>
        <el-form-item label="中文名称或者描述" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入中文名称或者描述" />
        </el-form-item>
        <el-form-item label="描述" prop="desc">
          <el-input v-model="form.desc" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="供应商" prop="suppier">
          <el-input v-model="form.suppier" placeholder="请输入供应商" />
        </el-form-item>
        <el-form-item label="联系人" prop="buyer">
          <el-input v-model="form.buyer" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="发货地址" prop="deliverySite">
          <el-input v-model="form.deliverySite" placeholder="请输入发货地址" />
        </el-form-item>
        <el-form-item label="是否特殊" prop="special">
          <el-radio-group v-model="form.special">
            <el-radio :label="1" :key="form.special" :value="1">是</el-radio>
            <el-radio :label="0" :key="form.special" :value="0">不是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="物流运输天数" prop="transport">
          <el-input v-model="form.transport" placeholder="请输入物流运输天数" />
        </el-form-item>
        <el-form-item label="库存预留天数" prop="bufferDay">
          <el-input v-model="form.bufferDay" placeholder="请输入库存预留天数" />
        </el-form-item>
        <el-form-item label="raw" prop="raw">
          <el-input v-model="form.raw" placeholder="请输入raw" />
        </el-form-item>
        <el-form-item label="rds" prop="rds">
          <el-input v-model="form.rds" placeholder="请输入rds" />
        </el-form-item>
        <el-form-item label="wip" prop="wip">
          <el-input v-model="form.wip" placeholder="请输入wip" />
        </el-form-item>
        <el-form-item label="库存" prop="total">
          <el-input v-model="form.total" placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="计划者coder" prop="plannerCode">
          <el-input v-model="form.plannerCode" placeholder="请输入计划者coder" />
        </el-form-item>
        <el-form-item label="是否显示" prop="isDelete">
<!--          <el-input v-model="form.isDelete" placeholder="请输入0非删除 1删除" />-->
          <el-radio-group v-model="form.isDelete">
            <el-radio :label="0">显示</el-radio>
            <el-radio :label="1">删除</el-radio>
          </el-radio-group>
        </el-form-item>
<!--        <el-form-item label="创建时间" prop="addTime">-->
<!--          <el-date-picker clearable-->
<!--                          v-model="form.addTime"-->
<!--                          type="date"-->
<!--                          value-format="yyyy-MM-dd"-->
<!--                          placeholder="请选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建人" prop="addBy">-->
<!--          <el-input v-model="form.addBy" placeholder="请输入创建人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="修改人" prop="updateBu">-->
<!--          <el-input v-model="form.updateBu" placeholder="请输入修改人" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listData, getData, delData, addData, updateData } from "@/api/system/erpOriginalData";
import {getToken} from "@/utils/auth";


export default {
  name: "Data",
  data() {
    return {
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
      // 原材料信息表格数据
      dataList: [],
      // 弹出层标题
      title: "",
      importTitle:"",
      // 是否显示弹出层
      open: false,
      // 导入弹窗
      importOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        coder: null,
        desc: null,
        suppier: null,
        buyer: null,
        deliverySite: null,
        special: null,
        transport: null,
        bufferDay: null,
        raw: null,
        rds: null,
        wip: null,
        total: null,
        plannerCode: null,
        isDelete: null,
        addTime: null,
        addBy: null,
        updateBu: null
      },
      // 表单参数
      form: {
        special:null
      },
      importForm:{
        fileList:[]
      },
      fileList:[],
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
        url: process.env.VUE_APP_BASE_API + "/excel/importStock"
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询原材料信息列表 */
    getList() {
      this.loading = true;
      listData(this.queryParams).then(response => {
        this.dataList = response.rows;
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
        coder: null,
        remark: null,
        desc: null,
        suppier: null,
        buyer: null,
        deliverySite: null,
        special: null,
        transport: null,
        bufferDay: null,
        raw: null,
        rds: null,
        wip: null,
        total: null,
        plannerCode: null,
        isDelete: null,
        addTime: null,
        addBy: null,
        updateTime: null,
        updateBu: null
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
      this.title = "添加原材料信息";
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
      getData(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改原材料信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addData(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除原材料信息编号为"' + ids + '"的数据项？').then(function() {
        return delData(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/data/export', {
        ...this.queryParams
      }, `data_${new Date().getTime()}.xlsx`)
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
    //  文件列表移除文件时的钩子
    handleRemove(file, fileList) {
      this.$message.warning("文件已移除");
    }
  }
};
</script>
