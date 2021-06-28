<template>
    <div class="query-details">
        <div class="title">
            <div>
                <i class="icon yysj" />
                <h2>请参与以下投票活动</h2>
            </div>
        </div>
        <div class="main">
            <div class="table">
                <div class="row">
                    <div class="l">
                        投票主题
                    </div>
                    <div class="r" style="display: flex;align-items: center;">
                        <P class="b"> {{ details.activityTitle }} {{ details.activityDesc }}</P>
                    </div>
                </div>
                <div class="row">
                    <div class="l">
                        投票时段
                    </div>
                    <div class="r">
                        <P class="b"> {{ details.startTime }} </P>
                        <P class="b"> {{ details.endTime }}</P>
                    </div>
                </div>
                <div class="row">
                    

                    <div class="r">
                        <van-checkbox-group  class="radio-cs" @change="memberChxChange" v-model="members">
                            <van-checkbox :key="index" :name="item.memberId" v-for="(item,index) in details.actMemberList" >
                            <p class="b f" ><img :src="item.candidatePhoto" class="g">{{item.candidateName}} </p>
                            </van-checkbox>
                        </van-checkbox-group>
                    </div>
                </div>
  
            </div>
            <div class="submitBtn">
                <van-button block type="info" @click="_addUserVote($event, details)" native-type="submit"
                :style="{ color: details.voteFlag === 0 ? '#FFFFFF' : '#999999' }"
                >
                    {{ details.voteFlag === 0 ? '提交投票' : '已投票,返回' }}
                </van-button>
            </div>
        </div>
    </div>
</template>
<script>
import {
    queryActivityDetail,
    addUserVote,
} from '@/api/index';
import { Dialog, Notify } from 'vant';
export default {
    data: () => ({
        activityId: 0,
        memberIds:[],
        details: {
        },
        members:[],
    }),
    watch: {},
    mounted() {
        this.activityId = this.$route.query.id;
        this._queryActivityDetail();
    },
    methods: {
    
        /**
         * 获取详情
         */
        _queryActivityDetail() {
            let info = {
                id: this.activityId,
            };
            queryActivityDetail(info).then((res) => {
                if (res.code && res.code === 1) {
                    this.details = res.data;
                }
            });
        },
       
        /**
         * 选中候选人
         */
        memberChxChange(data) {
 
            this.memberIds = [];
            let len = this.members.length;
            for (let i = 0; i < len; i++) {
                  this.memberIds.push(data[i]);  
            }
        },

        /**
         * 提交投票
         */
        _addUserVote(e, item) {
            e = e || window.event;
            if (e.stopPropagation) {
                e.stopPropagation();
            } else {
                e.cancelBubble = true; //IE阻止冒泡方法
            }
            if(item.voteFlag ==1){
                this.$router.push({
                    name: "query",
                    query: { },
                });
                return;
            }

            Dialog.confirm({
                title: '确认信息',
                message: '您选确认选中投票吗？',
            })
                .then(() => {
                    let info = {
                        activityId: this.activityId,
                        memberIds: this.memberIds,
                    };
                    addUserVote(info).then((res) => {
                        if (res.code && res.code === 1) {
                            Notify({ type: 'success', message: '投票成功' });
                            item.voteFlag = 1;
                        }
                    });
                })
                .catch(() => {});
        },
    },
};
</script>
<style lang="scss" scoped>
.title {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    box-sizing: border-box;
    padding: 0 10px;
    i {
        vertical-align: middle;
        width: 22px;
        height: 22px;
        margin: 0 6px 0 0px;
    }
    h2 {
        display: inline-block;
        font-size: 17px;
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
.main {
    width: 100%;
    padding: 20px 16px;
    background: #fff;
    box-sizing: border-box;
}
.table {
    width: 343px;
    border: 1px solid rgba(238, 241, 250, 1);
    border-radius: 5px;
}
.row {
    display: flex;
    border-bottom: 1px solid #eef1fa;
    .l {
        width: 85px;
        background: #4f82fa;
        text-align: center;
        padding: 16px 0;
        font-size: 12px;
        font-weight: 500;
        color: #fff;
    }
    .r {
        p {
            width: 250px;
            margin-bottom: 12px;
            padding-left: 15px;
            font-size: 13px;
            font-weight: 500;
            & > div {
                display: inline-block;
                // vertical-align: top;
                line-height: 18px;
                width: 150px;
            }
        }
        span {
            display: inline-block;
            width: 68px;
            color: #252525;
            line-height: 18px;
            margin-right: 10px;
            vertical-align: top;
        }
        .b {
            color: #252525;
        }
        .c {
            display: none;
            color: #4f82fa;
        }      
        .f {
            display: -webkit-flex;
            display: flex;
            -webkit-align-items: center;
            align-items: center;
            
        }
        .g{
            width: 30px;
            height: 30px;
            padding-right: 5px;
        }
    }
}
.btn {
    background: #fff;
    border: none;
    color: hsl(0, 72%, 60%);
}
.submitBtn {
    width: 100%;
    margin: 0 auto;
    padding: 10px 0;
}
::v-deep .van-field__button {
    display: flex;
    align-items: center;
}
.radio-cs {
    padding: 20px 20px;
    font-size: 16px;
    .van-checkbox {
      margin-bottom: 5px;
    }
}

</style>
