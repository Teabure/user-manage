<template>
  <div>
    <!-- 上方操作区 -->
    <el-row>
      <el-col :span="2">
        <el-button size="default" type="primary" @click="showCreateDialog">新建</el-button>
      </el-col>
      <el-col :span="4">
        <el-input placeholder="按关键字搜索" v-model="queryParams.query"></el-input>
      </el-col>
      <el-col :span="2">
        <el-button size="default" type="primary" @click="handleSearch">搜索</el-button>
      </el-col>
      <el-col :span="2">
        <el-button @click="undo()" size="default" type="warning" style="position:absolute;right:20px;">撤销</el-button>
      </el-col>
    </el-row>

    <!-- 表格内容 -->
    <el-table ref="multipleTable" :data="users.records" tooltip-effect="dark" style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column label="姓名" width="120" prop="name"></el-table-column>
      <el-table-column label="年龄" width="120" prop="age"></el-table-column>
      <el-table-column label="性别" width="120" prop="sex"></el-table-column>
      <el-table-column label="联系电话" width="120" prop="telephone"></el-table-column>
      <el-table-column label="详细地址">
        <template #default="{ row }">
          {{ row.provinceName }}/{{ row.cityName }}/{{ row.areaName }}/{{ row.address }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="140">
        <template #default="{ row }">
          <el-button @click="showEditDialog(row)" size="small" type="primary">编辑</el-button>
          <el-button @click="handleDelete(row)" size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!-- 批量删除和分页组件 -->
    <el-row>
      <el-col>
        <el-button type="danger" size="default" @click="removeBatch">批量删除</el-button>
      </el-col>
      <el-col style="position:absolute;right:20px;">
        <el-pagination small layout="prev, pager, next" :total="users.total" :page-size="queryParams.size"
          :current-page="queryParams.current" @current-change="handleCurrentChange">
        </el-pagination>
      </el-col>
    </el-row>


    <!-- 编辑或添加提示框 -->
    <el-dialog v-model="userformDialogVisible" title="新建/编辑用户" width="750px">
      <el-form :model="user" :rules="formRefRules" ref="formRef">
        <el-space :size="50">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="user.name" autocomplete="off" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="user.sex" placeholder="请选择性别" style="width: 300px;">
              <el-option label="男" value="男" />
              <el-option label="女" value="女" />
            </el-select>
          </el-form-item>
        </el-space>
        <el-space :size="50">
          <el-form-item label="年龄" prop="age">
            <el-input v-model.number="user.age" autocomplete="off" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="电话" prop="telephone">
            <el-input v-model="user.telephone" autocomplete="off" style="width: 300px;" />
          </el-form-item>
        </el-space>
        <el-space :size="30">
          <el-form-item label="地址" prop="valueOfSelected" >
            <el-cascader ref="cascader" v-model="user.valueOfSelected" :options="options" :props="props" @change="handleChange"
              style="width:300px" />
          </el-form-item>
          <el-form-item prop="address">
            <el-input v-model="user.address" autocomplete="off" placeholder="请输入详细地址" style="width: 350px;" />
          </el-form-item>
        </el-space>

      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="handleCancel">取消</el-button>
          <el-button type="primary" @click="handleSave">
            保存
          </el-button>
        </span>
      </template>
    </el-dialog>


  </div>
</template>

<script>
import { reactive, onMounted, ref } from 'vue'
import { getUserList, removeUser, undoUser, removeUserBatch, getUser, updateUser, createUser, districtAll } from '@/api/user'
export default {
  name: 'TableList',
  setup() {
    let userformDialogVisible = ref(false)
    let multipleSelection = ref([])
    let options = ref([])
    let user = reactive({
      id: "",
      name: "",
      age: 0,
      sex: "",
      telephone: "",
      provinceCode: "",
      provinceName: "",
      cityCode: "",
      cityName: "",
      areaCode: "",
      areaName: "",
      address: "",
      valueOfSelected: []
    })
    const cascader = ref()
    const formRef = ref()
    
    const validateArea = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error('请选择完整的省市区'));
      } else {
        callback();
      }
    }
    // 表单校验规则
    const formRefRules = ref({
      name: [
        { required: true, message: '请输入姓名', trigger: 'blur' }
      ],
      sex: [
        { required: true, message: '请选择性别', trigger: 'blur' }
      ],
      age: [
        { required: true, message: '请输入年龄', trigger: 'blur' },
        { type: 'number', message: '年龄必须为数字值', trigger: 'blur' }
      ],
      telephone: [
        { required: true, message: '请输入电话号码', trigger: 'blur' },
        { pattern: /^1[3456789]\d{9}$/, message: '电话号码格式不正确', trigger: 'blur' }
      ],
      valueOfSelected: [
        { required: true, message: '请选择省市区', trigger: 'blur' },
        { validator: validateArea, trigger: 'change' }
      ],
      address: [
        { required: true, message: '请输入详细地址', trigger: 'blur' }
      ]
    })
    const users = reactive({
      records: [],
      total: 0,
      size: 0,
      current: 0,
      pages: 0
    })
    const queryParams = reactive({
      query: "",
      current: 1,
      size: 5
    })
    const props = {
      value: 'code',
      label: 'name'
    }

    // 加载用户数据
    const loadUsers = async () => {
      const response = await getUserList(queryParams)
      users.records = response.data.records
      users.total = response.data.total
      users.size = response.data.size
      users.current = response.data.current
      users.pages = response.data.pages
    }

    // 页码发生变动时，加载用户数据
    const handleCurrentChange = (page) => {
      queryParams.current = page
      loadUsers()
    }

    // 删除某一行
    const handleDelete = async (row) => {
      await removeUser(row.id)
      loadUsers()
    }

    // 搜索
    const handleSearch = () => {
      loadUsers()
    }

    // 撤销
    const undo = async () => {
      await undoUser()
      loadUsers()
    }

    // 复选框选中
    const handleSelectionChange = (val) => {
      const arrNew = Array.from(val, ({ id }) => id)
      multipleSelection = arrNew
    }

    // 批量删除
    const removeBatch = async () => {
      await removeUserBatch(multipleSelection)
      loadUsers()
    }

    // 点击新建按钮
    const showCreateDialog = async () => {
      clearForm()
      userformDialogVisible.value = true
    }

    // 点击编辑按钮
    const showEditDialog = async (row) => {
      const response = await getUser(row.id)
      user.id = response.data.id
      user.name = response.data.name
      user.age = response.data.age
      user.sex = response.data.sex
      user.telephone = response.data.telephone
      user.provinceCode = response.data.provinceCode
      user.provinceName = response.data.provinceName
      user.cityCode = response.data.cityCode
      user.cityName = response.data.cityName
      user.areaCode = response.data.areaCode
      user.areaName = response.data.areaName
      user.address = response.data.address
      user.valueOfSelected = []
      user.valueOfSelected[0] = user.provinceCode
      user.valueOfSelected[1] = user.cityCode
      user.valueOfSelected[2] = user.areaCode
      userformDialogVisible.value = true
    }

    const clearForm = () => {
      user.id = ""
      user.name = ""
      user.age = null
      user.sex = ""
      user.telephone = ""
      user.provinceCode = ""
      user.provinceName = ""
      user.cityCode = ""
      user.cityName = ""
      user.areaCode = ""
      user.areaName = ""
      user.address = ""
      user.valueOfSelected = []
    }

    const handleCancel = () => {
      userformDialogVisible.value = false
    }

    // 点击保存按钮
    const handleSave = async () => {
      const isValid = await formRef.value.validate()
      if (!isValid) {
        return false;
      } else {
        userformDialogVisible.value = false
        delete user.valueOfSelected
        if (user.id == "") {
          await createUser(user)
        } else {
          await updateUser(user)
        }
        loadUsers()
      }
    }

    const handleChange = (value) => {
      user.provinceCode = value[0]
      user.cityCode = value[1]
      user.areaCode = value[2]
      const nodesLabels = cascader.value.getCheckedNodes()[0].pathLabels
      user.provinceName = nodesLabels[0]
      user.cityName = nodesLabels[1]
      user.areaName = nodesLabels[2]

    }

    onMounted(async () => {
      loadUsers()
      const response = await districtAll()
      options.value = response.data;
    })


    return {
      users,
      queryParams,
      userformDialogVisible,
      user,
      options,
      props,
      cascader,
      formRef,
      formRefRules,
      handleDelete,
      handleCurrentChange,
      handleSearch,
      handleSelectionChange,
      handleSave,
      handleChange,
      handleCancel,
      undo,
      removeBatch,
      showCreateDialog,
      showEditDialog
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
