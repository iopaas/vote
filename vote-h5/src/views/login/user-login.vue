<template>
    <div class="no-business">

        <div class="title">
            <i class="icon khxx" />
            <h2>用户信息</h2>
        </div>

        <van-cell-group>
            <van-field v-model="from.idCard" name="身份证号" label="身份证号" maxlength="10" placeholder="" input-align="right" />
            <van-field v-model="from.email" name="电子邮箱" label="电子邮箱" maxlength="100" placeholder="" input-align="right" />
        </van-cell-group>
        <div class="submitBtn">
            <van-button block type="info" @click="_userLogin()" native-type="submit">
                用户登记
            </van-button>
        </div>
    </div>
</template>
<script>
import { format, addMonths } from 'date-fns';
import { Notify } from 'vant';
import {
    userLogin,
} from '@/api/index';
import { setToken, getToken } from '@/utils/cache';
import {   getRouterParams, removeRouterParams } from '@/utils/cache';
export default {
    data: () => ({
        routerName: '',
        activityId: 0,
        from: {
            idCard: '', 
            email: '', 
        },
    }),
    mounted() {
        var routers = getRouterParams();
        this.routerName = routers.name;
        this.activityId = routers.queryId;
    },
    methods: {
        /**
         * 登录
         */
        _userLogin() {
            let info = {
                role_id: 2,
                idCard: this.from.idCard,
                email: this.from.email,
            };
            let rel = this.validateForm(info);
            if (rel.state) {
                userLogin(info).then((res) => {
                    if (res.code && res.code === 1) {
                        setToken(res.data.token);
                        removeRouterParams();
                        if(this.routerName){
                          this.toUrl(this.routerName, this.activityId);
                        }else{
                          this.toUrl('query', 0);
                        }
                    }
                });
            } else {
                Notify({
                    type: 'warning',
                    message: rel.error,
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
        /**
         * 校验参数
         */
        validateForm(data) {
            let rel = {
                state: true,
                error: '',
            };
            let validateArr = [
                { key: 'idCard', value: '请填写身份证号' },
                { key: 'email', value: '请填写电子邮箱' },
            ];
            for (let i = 0; i < validateArr.length; i++) {
                if (data[validateArr[i].key] === '请选择' || data[validateArr[i].key] === '' || data[validateArr[i].key] === null) {
                    rel.state = false;
                    rel.error = validateArr[i].value;
                    return rel;
                    break;
                }
            }

            const regCard = /^[A-Z]\d{6}\([\dA]\)$/;
            if (!regCard.test(data.idCard)) {
                rel = {
                    state: false,
                    error: '请输入正确的身份证号',
                };
                return rel;
            }
            
            const regEmail = /^([A-Za-z0-9_-|\.])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
            if (!regEmail.test(data.email)) {
                rel = {
                    state: false,
                    error: '请输入正确的电子邮箱',
                };
                return rel;
            }
             return rel;
        },
    },
};
</script>
<style lang="scss" scoped>
.title {
    display: flex;
    align-items: center;
    width: 100%;
    & > i {
        width: 22px;
        height: 22px;
        margin: 0 6px 0 14px;
    }
    & > h2 {
        font-size: 17px;
    }
}
.order-hour {
    & > p {
        font-size: 15px;
        font-weight: bold;
        text-align: center;
        margin-bottom: 30px;
    }
    & > ul {
        li {
            float: left;
            width: 50%;
            margin-bottom: 20px;
        }
        p {
            width: 80%;
            margin: 0 auto;
            height: 32px;
            text-align: center;
            box-sizing: border-box;
            border: 1px solid #efefef;
            border-radius: 16px;
            font-size: 15px;
            line-height: 32px;
        }
        p.active {
            color: #ffffff;
            background: #4f82fa;
            border: none;
        }
        p.no {
            color: #b4b4b4;
            background: #f4f4f4;
            border: none;
        }
    }
}
.tj,
.jj {
    width: 24px;
    height: 24px;
}
.order-project {
    padding: 45px 45px;
    font-size: 16px;
    .van-checkbox {
        margin-bottom: 15px;
    }
}
.submitBtn {
    width: 94%;
    margin: 0 auto;
    padding: 30px 0;
}
::v-deep .van-field__button {
    display: flex;
    align-items: center;
}
</style>
