<template>
    <div class="index">
        <el-container>
            <el-header class="header" style="height:90px">
                <div class="header-box">
                    <div class="df">
                        <h1 class="logo">
                            <p class="title">投票管理系统</p>
                        </h1>
                    </div>
                    <div class="sidebar-right">
                        <header-item />
                    </div>
                </div>
            </el-header>
            <el-container>
                <el-aside width="226px" class="aside scrollbar"><side-menu /></el-aside>
                <el-main class="main"><router-view /></el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import SideMenu from './components/sidebar/menu.vue';
import HeaderItem from './components/sidebar/header-item.vue';
export default {
    components: {
        SideMenu,
        HeaderItem,
    },
};
</script>
<style lang="scss" scoped>
$a: '90px';
.index {
    &,
    .el-container {
        height: 100%;
    }

    .header {
        background: #4f82fa;
        z-index: 1;
        box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
        height: 90px;
    }

    .main {
        background-color: #f0f2f5;
        position: absolute;
        top: 90px;
        left: 226px;
        right: 0;
        bottom: 0;
    }
    .el-main {
        padding: 15px;
        box-sizing: border-box;
    }
}

.header-box {
    width: auto;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
}
.logo {
    cursor: pointer;
    display: flex;
    align-items: center;
    margin: 25px 0 0 0px;
   
    & > .title {
        font-size: 26px;
        font-family: 'Source Han Sans CN', '微软雅黑';
        font-weight: bold;
        color: rgba(255, 255, 255, 1);
        margin-left: 0px;
        letter-spacing: 4px;
    }
}
.sidebar-right {
    display: flex;
    align-items: flex-end;
}
</style>
