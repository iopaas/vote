<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style lang="scss">
html,
body,
#app {
  min-height: 100%;
  background: #eef1fa;
}
::v-deep {
  .van-popup.van-popup--top.van-notify.van-notify--warning {
    background-color: #ff976a;
  }
  .van-popup.van-popup--top.van-notify.van-notify--success {
    background-color: #07c160;
  }
}
@import "./assets/style/index.scss";
</style>
