<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品线" prop="productLineCode">
        <el-input
          v-model="queryParams.productLineCode"
          placeholder="请输入产品线"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品型号code" prop="productModelCode">
        <el-input
          v-model="queryParams.productModelCode"
          placeholder="请输入产品型号code"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品个数" prop="count">
        <el-input
          v-model="queryParams.count"
          placeholder="请输入产品个数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原材料code" prop="originalCoder">
        <el-input
          v-model="queryParams.originalCoder"
          placeholder="请输入原材料code"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用到的个数" prop="number">
        <el-input
          v-model="queryParams.number"
          placeholder="请输入用到的个数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用到这个原材料的日期" prop="date">
        <el-input
          v-model="queryParams.date"
          placeholder="请输入用到这个原材料的日期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="0 非删除 1删除" prop="isDelete">
        <el-input
          v-model="queryParams.isDelete"
          placeholder="请输入0 非删除 1删除"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="addTime">
        <el-date-picker clearable
                        v-model="queryParams.addTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择${comment}">
        </el-date-picker>
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
          v-hasPermi="['system:item:add']"
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
          v-hasPermi="['system:item:edit']"
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
          v-hasPermi="['system:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:item:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
<!--      <el-table-column label="产品线" align="center" prop="productLineCode" />-->
      <el-table-column label="产品型号code" align="center" prop="productModelCode" />
      <el-table-column label="产品个数" align="center" prop="count" />
      <el-table-column label="原材料code" align="center" prop="originalCoder" />
      <el-table-column label="用到的个数" align="center" prop="number" />
      <el-table-column label="用到这个原材料的日期" align="center" prop="date" />
<!--      <el-table-column label="0 非删除 1删除" align="center" prop="isDelete" />-->
      <el-table-column label="导入日期" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:item:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:item:remove']"
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

    <!-- 添加或修改用于保存 每天生产计划所消耗的原材料数对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品线" prop="productLineCode">
          <el-input v-model="form.productLineCode" placeholder="请输入产品线" />
        </el-form-item>
        <el-form-item label="产品型号code" prop="productModelCode">
          <el-input v-model="form.productModelCode" placeholder="请输入产品型号code" />
        </el-form-item>
        <el-form-item label="产品个数" prop="count">
          <el-input v-model="form.count" placeholder="请输入产品个数" />
        </el-form-item>
        <el-form-item label="原材料code" prop="originalCoder">
          <el-input v-model="form.originalCoder" placeholder="请输入原材料code" />
        </el-form-item>
        <el-form-item label="用到的个数" prop="number">
          <el-input v-model="form.number" placeholder="请输入用到的个数" />
        </el-form-item>
        <el-form-item label="用到这个原材料的日期" prop="date">
          <el-input v-model="form.date" placeholder="请输入用到这个原材料的日期" />
        </el-form-item>
        <el-form-item label="0 非删除 1删除" prop="isDelete">
          <el-input v-model="form.isDelete" placeholder="请输入0 非删除 1删除" />
        </el-form-item>
        <el-form-item label="${comment}" prop="addTime">
          <el-date-picker clearable
                          v-model="form.addTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择${comment}">
          </el-date-picker>
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
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/system/erpProductionPlanItem";

export default {
  name: "Item",
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
      // 用于保存 每天生产计划所消耗的原材料数表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productLineCode: null,
        productModelCode: null,
        count: null,
        originalCoder: null,
        number: null,
        date: null,
        isDelete: null,
        addTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用于保存 每天生产计划所消耗的原材料数列表 */
    getList() {
      this.loading = true;
      listItem(this.queryParams).then(response => {
        this.itemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        productLineCode: null,
        productModelCode: null,
        count: null,
        originalCoder: null,
        number: null,
        date: null,
        isDelete: null,
        addTime: null
      };
      this.resetForm("form");
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
      this.title = "添加用于保存 每天生产计划所消耗的原材料数";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getItem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用于保存 每天生产计划所消耗的原材料数";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateItem(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addItem(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用于保存 每天生产计划所消耗的原材料数编号为"' + ids + '"的数据项？').then(function() {
        return delItem(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('/excelExport/daysPlan', {
        ...this.queryParams
      }, `item_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
