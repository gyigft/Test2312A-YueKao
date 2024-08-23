<template>
  <el-button type="success" @click="toAdd">New Role</el-button>
  <el-button type="danger" @click="once">Batch Delete</el-button>
  <!--  <el-button type="success" @click="Tomb">Tombstone</el-button>-->
  <el-form :inline="true" :model="Vague" class="模糊查询">
    <el-form-item>
      <el-input v-model="Vague.name" placeholder="请输入关键字" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="mocha">Select</el-button>
    </el-form-item>
  </el-form>
  <!--- 角色表格数据 -->
  <el-table
    ref="multipleTableRef"
    :data="tableData"
    style="width: 100%"
    @selection-change="handleSelectionChange"
  >
    <el-table-column type="selection" width="55" />
    <el-table-column type="index" label="序号" width="120" />
    <el-table-column property="typeid" label="类别编号" />
    <el-table-column property="name" label="类别名称" />
    <el-table-column property="fid" label="父类编号" />
    <el-table-column property="tdengji" label="类别等级">
      <template #default="scope">
        <el-tag v-if="scope.row.tdengji === 0" type="success">一级</el-tag>
        <el-tag v-if="scope.row.tdengji === 1" type="success">二级</el-tag>
      </template>
    </el-table-column>
    <!--    <el-table-column property="is_deleted" label="逻辑删除"/>-->
    <el-table-column>
      <template #default="scope">
        <el-button type="primary" @click="handleDelete(scope.row)">
          Edit
        </el-button>
      </template>
    </el-table-column>
  </el-table>

  <!--分页条-->
  <el-pagination
    v-model:current-page="pageNum"
    v-model:page-size="pageSize"
    :page-sizes="[5, 10, 15, 20]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />

  <!-- 对话框 -->
  <el-dialog v-model="dialogVisible" :before-close="handleClose">
    <el-form :model="AddForm" status-icon :rules="rules" class="demo-ruleForm">
      <el-form-item label="类别编号" prop="typeid">
        <el-input v-model="AddForm.typeid" />
      </el-form-item>

      <el-form-item label="类别名称" prop="name">
        <el-input v-model="AddForm.name" />
      </el-form-item>
      <el-form-item label="父类编号" prop="fid">
        <el-input v-model="AddForm.fid" />
      </el-form-item>
      <el-form-item label="类别等级" prop="tdengji">
        <el-radio-group v-model="AddForm.tdengji">
          <el-radio-button label="0">一级</el-radio-button>
          <el-radio-button label="1">二级</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消添加</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
import {
  PostTianMao,
  PostAdd,
  PostUpdate,
  GetVague,
  DeleteDelss,
} from '@/api/tianmao'
import { ElMessage } from 'element-plus'

const tableData = ref([])
const dialogVisible = ref(false)

// 新增修改对话框
const AddForm = ref({
  id: '',
  typeid: '',
  fid: '',
  name: '',
  tdengji: '',
})
// 模糊查询
const Vague = ref({
  name: '',
})

// const sysUser = ref(form)

// 分页条总记录数
const pageNum = ref(1)
const pageSize = ref(5)
const total = ref(50)
const params = ref({
  pageNum: pageNum.value,
  pageSize: pageSize.value,
})

// 分页方法
// eslint-disable-next-line no-unused-vars
function handleSizeChange(val) {
  params.value.pageSize = val
  getList()
}

// eslint-disable-next-line no-unused-vars
function handleCurrentChange(val) {
  params.value.pageNum = val
  getList()
}

// 列表 + 分页
function getList() {
  PostTianMao(params.value).then(res => {
    console.log(res.data)
    tableData.value = res.data.records
    total.value = res.data.total
  })
}

// 点击新增，打开对话框
// eslint-disable-next-line no-unused-vars
function toAdd() {
  dialogVisible.value = true
}

// 点击编辑
// eslint-disable-next-line no-unused-vars
function handleDelete(val) {
  dialogVisible.value = true
  AddForm.value = val
}

// 当id不等于空进入修改，否则进入新增
// eslint-disable-next-line no-unused-vars
function submitForm() {
  if (AddForm.value.id != '') {
    PostUpdate(AddForm.value).then(res => {
      dialogVisible.value = false
      ElMessage.success('修改成功')
      getList()
    })
  } else {
    PostAdd(AddForm.value).then(res => {
      dialogVisible.value = false
      ElMessage.success('添加成功')
      getList()
    })
  }
}

// 点击对话框里的取消
function cancel() {
  dialogVisible.value = false
  AddForm.value = ref({
    id: '',
    typeid: '',
    fid: '',
    name: '',
    tdengji: '',
  })
}

// 让多选框拿到id
const array = []

// eslint-disable-next-line no-unused-vars
function handleSelectionChange(dxk) {
  array.value = []
  dxk.forEach(e => {
    array.value.push(e.id)
  })
}

function once() {
  if (confirm('确认删除所选信息吗?')) {
    DeleteDelss(array.value).then(res => {
      getList()
    })
  }
}

// 模糊查询
function mocha() {
  GetVague(Vague.value).then(res => {
    console.log(res.data)
    tableData.value = res.data
  })
}

// eslint-disable-next-line no-unused-vars
function handleClose() {
  dialogVisible.value = false
  AddForm.value = {}
}

// function Tomb(){
//   Tombstone().then(res => {
//     getList()
//   })
// }

getList()
</script>
