<template>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <div class="el-group">
            <el-form-item label="图书编号">
                <el-input readOnly type="text" v-model="ruleForm.id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="图书名称" prop="name">
                <el-input type="text" v-model="ruleForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="作者" prop="author">
                <el-input v-model="ruleForm.author"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </div>
    </el-form>
</template>
<script>
    export default {
        data() {
            var checkAuthor = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('作者不能为空'));
                }else{
                    callback();
                }
            };
            var validateName = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入图书名称'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    id: '',
                    name: '',
                    author: ''
                },
                rules: {
                    name: [
                        { required: true, validator: validateName, trigger: 'blur' }
                    ],
                    author: [
                        { required: true, validator: checkAuthor, trigger: 'blur' }
                    ]
                }
            };
        },
        created() {
            this.ruleForm.id = this.$route.query.row.id;
            this.ruleForm.name = this.$route.query.row.name;
            this.ruleForm.author = this.$route.query.row.author;
    },
        methods: {
            submitForm(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.axios
                            .put("http://localhost:8002/book/update", this.ruleForm)
                            .then(function (resp){
                                if("success" == resp.data){
                                    _this.$alert('更新成功', _this.ruleForm.name, {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            _this.$router.push("/");
                                        }
                                    });
                                }else{
                                    _this.$message("出现异常！");
                                }
                            });
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style>
    .el-group .el-input__inner{
        width: 40%;
    }
</style>