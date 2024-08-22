<template>
  <div class="search-div">
    <!-- 搜索表单 -->
    <el-form label-width="70px" size="small">
      <el-form-item label="角色名称">
        <el-input
          v-model="queryDto.roleName"
          style="width: 100%"
          placeholder="角色名称"
        ></el-input>
      </el-form-item>
      <el-row style="display:flex">
        <el-button type="primary" size="small" @click="searchSysRole">
          搜索
        </el-button>
        <el-button size="small">重置</el-button>
      </el-row>
    </el-form>

    <!-- 添加按钮 -->
    <div class="tools-div">
      <el-button type="success" size="small" @click="addShow">添 加</el-button>
    </div>
    <!-- 添加角色表单对话框 -->
    <!-- 页面表单 -->
    <el-dialog v-model="dialogVisible" title="添加或修改角色" width="30%">
      <el-form label-width="120px">
        <el-form-item label="角色名称">
          <el-input v-model="sysRole.roleName" />
        </el-form-item>
        <el-form-item label="角色Code">
          <el-input v-model="sysRole.roleCode" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!--- 角色表格数据 -->
    <el-table :data="list" style="width: 100%">
      <el-table-column prop="roleName" label="角色名称" width="180" />
      <el-table-column prop="roleCode" label="角色code" width="180" />
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column label="操作" align="center" width="280" #default="scope">
        <el-button type="primary" size="small" @click="editShow(scope.row)">
          修改
        </el-button>
        <el-button type="danger" size="small" @click="deleteById(scope.row)">
          删除
        </el-button>
        <el-button
          type="warning"
          size="small"
          @click="showAssignMenu(scope.row)"
        >
          分配菜单
        </el-button>
      </el-table-column>
    </el-table>

    <!-- 分配菜单的对话框
    // tree组件添加ref属性，后期方便进行tree组件对象的获取
    -->
    <el-dialog v-model="dialogMenuVisible" title="分配菜单" width="40%">
      <el-form label-width="80px">
        <el-tree
          :data="sysMenuTreeList"
          ref="tree"
          show-checkbox
          default-expand-all
          :check-on-click-node="true"
          node-key="id"
          :props="defaultProps"
        />
        <el-form-item>
          <el-button type="primary">提交</el-button>
          <el-button @click="dialogMenuVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!--分页条-->
    <el-pagination
      :current-page="pageParams.currentPage"
      :page-size="pageParams.pageSize"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next"
      :total="total"
      @current-change="fetchData"
      @size-change="fetchData"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import {
  GetRoleListByPage,
  SaveSysRole,
  UpdateSysRole,
  DeleteSysRoleById,
  GetSysRoleMenuIds,
} from '@/api/system'
import { ElMessage, ElMessageBox } from 'element-plus'

// 分页条总记录数
const total = ref(0)

//定义分页对象
const pageParams = ref({
  currentPage: 1,
  pageSize: 10,
})
//queryDto
const queryDto = ref({
  roleName: '',
})

// 定义表格数据模型
const list = ref([
  /*
  {"id":9 ,  "roleName": "系统管理员" , "roleCode":"xtgly","createTime": '2023-07-31'},
  {"id":10 , "roleName": "商品管理员" , "roleCode":"spgly","createTime": '2023-07-31'}
*/
])
//钩子函数初始化
onMounted(() => {
  // 获取分页条数据
  // getPage()
  // console.log("初始化")
  fetchData()
})
//获取后台数据函数
const fetchData = async () => {
  const { code, message, data } = await GetRoleListByPage(
    pageParams.value.currentPage,
    pageParams.value.pageSize,
    queryDto.value
  )
  // if (+code==200){
  //赋值
  list.value = data.list
  total.value = data.total
  // }
}

// 搜索按钮点击事件处理函数
const searchSysRole = () => {
  //queryDto.value.roleName = ""
  fetchData()
}

// 控制对话是否展示的变量
const dialogVisible = ref(false)

//进入添加
const addShow = () => {
  sysRole.value = defaultForm
  dialogVisible.value = true
}
//表单数据模型
const defaultForm = {
  id: '',
  roleCode: '',
  roleName: '',
}
const sysRole = ref(defaultForm) // 使用ref包裹该对象，使用reactive不方便进行重置

// 添加角色
const submit = async () => {
  if (!sysRole.value.id) {
    const { code } = await SaveSysRole(sysRole.value)
    if (code === 200) {
      dialogVisible.value = false
      ElMessage.success('操作成功')
      fetchData()
    }
  } else {
    const { code } = await UpdateSysRole(sysRole.value)
    if (code === 200) {
      dialogVisible.value = false
      ElMessage.success('操作成功')
      fetchData()
    }
  }
}
// 修改按钮点击事件处理函数
const editShow = row => {
  sysRole.value = row
  dialogVisible.value = true
}

// 删除数据
const deleteById = row => {
  ElMessageBox.confirm('此操作将永久删除该记录, 是否继续?', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    const { code } = await DeleteSysRoleById(row.id)
    if (code === 200) {
      ElMessage.success('删除成功')
      pageParams.value.page = 1
      fetchData()
    }
  })
}
const defaultProps = {
  children: 'children',
  label: 'title',
}
const dialogMenuVisible = ref(false)
const sysMenuTreeList = ref([])

// 树对象变量
const tree = ref()

// 默认选中的菜单数据集合
let roleId = ref()
const showAssignMenu = async row => {
  dialogMenuVisible.value = true
  roleId = row.id
  const { data } = await GetSysRoleMenuIds(row.id) // 请求后端地址获取所有的菜单数据，以及当前角色所对应的菜单数据
  sysMenuTreeList.value = data.sysMenuList
  tree.value.setCheckedKeys(data.roleMenuIds) // 进行数据回显
}
</script>

<style scoped>
.search-div {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}

.tools-div {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}
</style>
