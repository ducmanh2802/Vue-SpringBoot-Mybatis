<template>
    <div>
        <el-table :data="books" border style="width: 100%" fit>
            <el-table-column prop="id" sortable label="编号" width="140">
            </el-table-column>
            <el-table-column fit prop="name" label="书名" width="300px">
            </el-table-column>
            <el-table-column prop="author" label="作者">
            </el-table-column>
            <el-table-column
                    align="right">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                @current-change="page"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        inject: ['reload'],
        name: "Book",
        data() {
            return {
                books: null,
                pageSize: 10,
                total: null,
            }
        },
        methods: {
            page(current_page) {
                const _this = this;
                this.axios
                    .get("http://localhost:8002/book/findAll/" + current_page + "/" + this.pageSize)
                    .then(function (response) {
                        _this.books = response.data.list;
                        _this.total = response.data.total;
                    });
            },
            handleEdit(index, row) {

                this.$router.push({
                    path: '/updateBook',
                    query: {row: row}
                });
            },
            handleDelete(index, row) {
                const _this = this;
                this.axios.delete("http://localhost:8002/book/delete/" + row.id).then(function (response){
                    _this.$confirm('此操作将永久删除该记录, 是否继续?', '删除确认', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        if("success" == response.data){
                            _this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            _this.$router.go(0);
                        }else{
                            _this.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                    }).catch(() => {
                        _this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
                });
            }
        },
        created() {
            const _this = this;
            this.axios
                .get("http://localhost:8002/book/findAll/1/" + _this.pageSize )
                .then(function (response) {
                    _this.books = response.data.list;
                    _this.total = response.data.total;
                });
        }
    }
</script>

<style scoped>

</style>