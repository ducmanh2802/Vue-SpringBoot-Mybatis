<template>
    <el-container style="height: 750px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="['0','1']">
                <el-submenu v-for="(nav,index) in $router.options.routes" :index="index+''">
                    <template slot="title">
                        <i class="el-icon-message"></i>{{nav.name}}
                    </template>
                    <el-menu-item v-for="(nav2, index2) in nav.children" :index="nav2.path" :class="nav2.path==$route.path?'is-active':''"  v-if="nav2.show">
                        {{nav2.name}}
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>


        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <span>PanzVor</span>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
    .el-container a.router-link-exact-active {
        color: #42b983;
    }
    .el-container a {
        text-decoration: none;
        color: #2c3e50;
    }
</style>

<script>
    export default {
        name: "Home"
    };
</script>