<template>
  <el-menu class="menu" :default-active="activeRouter">
    
    <!--
    <el-menu-item index="/stat">
      <router-link to="/stat">
        <i class="el-icon-date"></i>投票統計</router-link
      >
    </el-menu-item>
    -->

    <el-menu-item index="/member">
      <router-link to="/member">
        <i class="el-icon-user"></i>候选人管理</router-link
      >
    </el-menu-item>
    
    <el-menu-item index="/activity">
      <router-link to="/activity">
        <i class="el-icon-share"></i>投票活动管理</router-link
      >
    </el-menu-item>

 
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      activeRouter: "/",
    };
  },
  watch: {
    $route() {
      this.activeRouter = this.$route.path;
    },
  },
  created() {
    this.activeRouter = this.$route.path;
  },
  mounted() {},
};
</script>

<style lang="scss" scoped>
.el-menu {
  background-color: transparent;
  border-right-width: 0;

  .el-menu-item.is-active {
    background-color: #f0f2f5;
    text-align: left;
    a,
    span,
    i {
      color: #409EFF;
    }
  }
  a {
    display: block;
  }
  a,
  span,
  i {
    color: #333333;
  }
}

.el-menu:not(.el-menu--collapse) {
  width: 226px;
}

.el-menu-item [class^="iconfont"],
.el-submenu [class^="iconfont"] {
  vertical-align: middle;
  margin-right: 5px;
  width: 24px;
  text-align: center;
  font-size: 18px;
}

::v-deep .el-submenu .el-menu {
  background-color: rgba(255, 255, 255, 0.05);
}
.el-menu-item:focus,
.el-menu-item:hover,
::v-deep .el-submenu__title:hover {
  background-color: rgba(255, 255, 255, 0.1);
}
</style>
