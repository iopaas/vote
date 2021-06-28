<template>
  <div class="home">
    <div
      class="box"
      v-for="(item, index) in list"
      :key="index"
      @click="toUrl(item.urlName)"
    >
      <h3>{{ item.name }}</h3>
      <i class="icon jr" />
    </div>
  </div>
</template>
<script>
export default {
  data: () => ({
    list: [
      {
        name: "用户投票",
        urlName: "query",
      },
      {
        name: "用户登记",
        urlName: "login",
      },
      {
        name: "数据分析",
        urlName: "analysis",
      },
    ],
  }),
  watch: {},
  created() {
    this.getUrlParams();
  },
  mounted() {},
  methods: {
    /*
    跳转地址 
    */
    toUrl(name) {
      this.$router.push({
        name: name,
      });
    },
    /*
     获取url参数
    */
    getUrlParams() {
      if (
        this.$route.query &&
        this.$route.query.token
      ) {
        this.$cache.addToken(
          this.$route.query.token
        );
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.home {
  position: absolute;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  // align-items: center;
  // padding-top: 25px;
}
.box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 375px;
  height: 185px;
  & > h3 {
    margin-left: 178px;
    font-size: 18px;
  }
  & > i {
    width: 11px;
    height: 10px;
    // margin-left: 66px;
    margin-right: 43px;
  }
}
.box:nth-child(1) {
  background: url("~@/assets/images/yj.png");
  background-size: cover;
}
.box:nth-child(2) {
  background: url("~@/assets/images/yycx.png");
  background-size: cover;
}
.box:nth-child(3) {
  background: url("~@/assets/images/xtyw.png");
  background-size: cover;
}
</style>
