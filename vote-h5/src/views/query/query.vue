<template>
    <div class="content">
        <van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="_queryActivityPage()" ref="list">
            <div class="card" v-for="item in list" :key="item.id">

                <div class="card-main">

                    <p><span>{{ item.id }}.{{ item.activityTitle }}{{ item.activityDesc }}</span></p>
                    <p><span>投票时间：</span>{{ item.startTime }}</p>
                    <p><span>截止时间：</span>{{ item.endTime }}</p>
                    <p><span>投票人数：</span>{{ item.votes }}</p>
                    <p><span>候选人员：</span></p>
                    <div class="mr" v-for="item1 in item.actMemberList" :key="item1.id">

                        <p class="f">
                        <van-badge :content="item1.votes" class="bg"></van-badge>
                        <img :src="item1.candidatePhoto" class="g">
                        <span class="f">{{ item1.candidateName }}</span><span>
                        </span>
                        </p>
                    </div>

                </div>
                <div class="card-header">
                    <div class="r" :style="{ color: item.voteFlag === 1 ? '#4CC574' : '#999999' }">
                        <span>{{ item.voteFlag === 1 ? '已投票' : '未投票' }}</span>
                        <van-icon name="arrow" />
                    </div>
                </div>
                <van-button
                        block
                        type="info"
                        @click="toUrl('query-details', item.id)"
                        native-type="submit"
                        class="btn"
                        :disabled="item.enabled == 0"
                        :style="{ background: item.voteFlag === 1 ? '#C6CCDB' : '#4F82FA' }"
                    >
                        参与投票
                </van-button>
            </div>
        </van-list>
        <back-to-top :custom-style="myBackToTopStyle" :visibility-height="300" :back-position="0" transition-name="fade" />
    </div>
</template>
<script>
import {
    queryActivityPage,
} from '@/api/index';
import BackToTop from '@/components/BackToTop';
import { Dialog, Notify, Badge } from 'vant';
export default {
    data: () => ({
        myBackToTopStyle: {
            right: '50px',
            bottom: '20px',
            width: '45px',
            height: '45px',
            'border-radius': '20px',
            // 请保持与高度一致以垂直居中 Please keep consistent with height to center vertically
            background: '#fff', // 按钮的背景颜色 The background color of the button
        },
        finished: false,
        loading: false,
        list: [
        ],
        pageNum: 0, // 第几个
        pageSize: 10, // 一页几个
        total: 0, //总数量
    }),
    components: { BackToTop },
    watch: {},
    mounted() {},
    methods: {
        /**
         * 查询预约订单分页查询(每次10条)
         */
        _queryActivityPage() {
            if (!this.finished) {
                this.pageNum = this.pageNum + 1;
                let info = {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                };
                queryActivityPage(info).then((res) => {
                    if (res.code && res.code === 1) {
                        if (res.data.length < this.pageSize) {
                            this.finished = true;
                        }
                        this.loading = false;
                        this.list = this.list.concat(res.data);
                    }
                });
            }
        },
        /*
    跳转地址
    */
        toUrl(name, id) {
            this.$router.push({
                name: name,
                query: {
                    id: id,
                },
            });
        },
    },
};
</script>
<style lang="scss" scoped>
// .van-list {
//     position: absolute;
//     width: 100%;
//     height: 100%;
// }
.card {
    position: relative;
    width: 343px;
    // height: 163px;
    background: #fff;
    margin: 20px auto;
    border-radius: 8px;
    box-shadow: 0px 3px 11px 4px rgba(0, 0, 0, 0.03);
    padding: 12px;
    box-sizing: border-box;
}
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    margin-top: 20px;
    & > .l {
        display: flex;
        & > div {
            display: inline-block;
        }
        h3 {
            display: inline-block;
            font-size: 16px;
            line-height: 22px;
            margin: 0;
            margin-right: 8px;
            width: 180px;
        }
        .order-type {
            width: 44px;
            height: 22px;
            border-radius: 10px;
            background: #ff943c;
            color: #fefefe;
            font-size: 12px;
            line-height: 22px;
            text-align: center;
            vertical-align: middle;
        }
    }
    .r {
        display: flex;
        align-items: right;
        font-size: 13px;
        font-weight: 500;
        line-height: 15px;
        // vertical-align: middle;
        i {
            font-size: 13px;
            // vertical-align: text-bottom;
        }
    }
}
.card-main {
    & > p {
        font-size: 13px;
        line-height: 22px;
        color: #252525;
        width: auto;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        margin-left: 0;
    }
     & > div > p  {
        font-size: 13px;
        line-height: 22px;
        color: #252525;
        width: auto;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        margin: 0;
    }
    .mr {
       padding-top: 0px;    
       line-height: 30px;
    }
    .mr >p{
        line-height: 30px;
        text-overflow: ellipsis;
        padding-left: 5px;
    }
    .mr >p > span{
        height: 30px; 
        line-height: 30px;
        text-overflow: ellipsis;
        margin: 0 auto;

    }
    .mr .f {
        vertical-align: 10px;
        margin-left: 5px;
    }
    .mr .g{
          width: 30px;
          height: 30px;
          margin-left: 5px;
          text-align: left;
    }  
    .mr .p{
            color: #4f82fa;
           // padding-left: 5px;
    }
    .bd {
        margin-top: 10px;
        margin-right: 40px;
    }
}
.bg{
    background-color: #f2f3f5;
    color: #4fc08d;
    vertical-align: 10px;
    margin-left: 3px;
    line-height: 30px;
    min-width: 32px;
}
.btn {
    position: absolute;
    right: 15px;
    bottom: 20px;
    width: auto;
    height: 32px;
    font-size: 12px;
    border: none;
}

</style>
