<template>
    <div class="success-page">
        <img src="@/assets/images/pic@2x.png" />
        <h3>数据分析</h3>
        <p>抱歉,此功能暂未开放!</p>
        <van-button block type="info" @click="toUrl('home')" class="btn" native-type="submit">
            返回
        </van-button>
    </div>
</template>
<script>
export default {
    data: () => ({}),
    watch: {},
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
    },
};
</script>
<style lang="scss" scoped>
.success-page {
    position: absolute;
    width: 100%;
    min-height: 100%;
    display: flex;
    flex-direction: column;
    // justify-content: center;
    background: #fff;
    img {
        display: inline-block;
        width: 231px;
        height: 216px;
        margin: 58px auto 32px auto;
        // border: 1px dashed #cccccc;
    }
    h3 {
        font-size: 20px;
        text-align: center;
        // line-height: 6px;
    }
    p {
        font-size: 15px;
        color: #a2a2a2;
        text-align: center;
        line-height: 0px;
    }
    .btn {
        margin-top: 76px;
        width: 172px;
        height: 37px;
        margin: 76px auto 0 auto;
    }
}
</style>
