<template>
  <div class="header-item">
    <img
      style="width:43px;height:43px"
      src="@/assets/images/touxiang.png"
      alt=""
    />
    <span class="account">{{ account }} </span>
    <el-dropdown
      class="user-setting"
      trigger="click"
      @command="userSettingCommand"
    >
      <span class="el-dropdown-link">
        <i class="el-icon-caret-bottom"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="logout">退出账号</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
// import { accountLogout } from "@/api/index";
import { getAccount, removeToken, removeAccount } from "@/utils/auth";
export default {
  data() {
    return {
      userInfo: {},
      account: getAccount(),
    };
  },
  created() {
    // this.activeRouter = this.$route.path;
  },
  mounted() {},
  methods: {
    /**
     * 用户设置对话框指令
     * @param {String} command 指令值
     */

    userSettingCommand(command) {
      switch (command) {
        case "logout":
          this.signOut();
          break;
        default:
          break;
      }
    },
    /**
     * 退出登录
     */
    signOut() {
      removeToken();
      removeAccount();
      this.$router.push({ name: "login" });
    },
  },
};
</script>
<style lang="scss" scoped>
.header-item {
  & > img {
    vertical-align: middle;
  }
  & > .account {
    font-size: 19px;
    margin: 0 10px;
    color: #fff;
    font-weight: 500;
  }
}
.el-badge {
  vertical-align: unset;
  margin-right: 30px;
}
.icongouwucheman {
  font-size: 25px;
  color: #666;
}
.el-icon-user-solid {
  font-size: 25px;
  color: #666;
}
.secant {
  display: inline-block;
  width: 1px;
  height: 10px;
  margin: 0 5px;
  background: #c5c3c3;
}
.h-button {
  .el-button {
    font-size: 15px;
  }
}
.el-dropdown-link {
  cursor: pointer;
}
.el-dropdown-menu {
  width: 140px;
}
</style>
