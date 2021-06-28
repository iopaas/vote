<template>
    <div class="login">
        <h1 class="login-logo">
            <p class="title">Voting management system</p>
        </h1>
        <div class="right">
            <el-form :model="form" :rules="rules" ref="loginForm" class="login-form" label-width="0px">
                <div class="login-title">
                    欢迎登录
                </div>
                <el-form-item prop="username">
                    <el-input v-model="form.username" @focus="clear_error()" placeholder="admin">
                        <template slot="prepend">
                            <img src="@/assets/images/zh.png" />
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item class="item-gap-min" prop="password">
                    <el-input
                        @keyup.enter.native="submitLoginForm('loginForm')"
                        type="password"
                        v-model="form.password"
                        placeholder="123456"
                        @focus="clear_error()"
                        :show-password="true"
                    >
                        <template slot="prepend">
                            <img src="@/assets/images/mima.png" />
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item class="item-gap-min">
                    <el-checkbox size="medium" v-model="remember" @change="rememberChange">记住密码</el-checkbox>
                </el-form-item>
                <el-form-item class="btn">
                    <el-button type="primary" @click="submitLoginForm('loginForm')" :disabled="loading">
                        <template v-if="!loading">Login</template>
                        <template v-else> <i class="el-icon-loading"></i>Loading </template>
                    </el-button>
                </el-form-item>

                <div class="errortitle">{{ errortitle }}</div>
            </el-form>
        </div>

        <div class="login-bg"></div>
    </div>
</template>
<script>
import { accountLogin } from '@/api/index';
import { setToken, setAccount } from '@/utils/auth';
import crypto from 'crypto';
import { getMD5 } from '@/utils';
import secret from '@/utils/secret';
export default {
    name: 'login',
    components: {},
    data() {
        /* 验证帐号不能为空 */
        let validateAccount = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入账号'));
            } else {
                callback();
            }
        };

        /* 验证密码不能为空 */
        let validatePassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                callback();
            }
        };

        return {
            form: {
                username: '', //帐号
                password: '', //密码
            },
            rules: {
                username: [{ validator: validateAccount, trigger: 'blur' }],
                password: [
                    {
                        validator: validatePassword,
                        trigger: 'blur',
                    },
                ],
            },
            loading: false,
            remember: false, //记住密码
            errortitle: '',
        };
    },
    created() {
        // if (window.localStorage.userInfo) {
        //     this.$router.push('/');
        // }
    },
    mounted() {
        this.fillAccounPassword(); //填充帐号密码
    },

    methods: {
        clear_error() {
            this.errortitle = '';
        },
        /**
         * 提交登录表单
         * @param {String} formName 表单名称
         */
        submitLoginForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.loging = true;
                    let info = {
                        account_number: this.form.username,
                        account_pwd: getMD5(this.form.password),
                        role_id: 1
                    };
                    accountLogin(info)
                        .then((res) => {
                            if (res.code && res.code === 1) {
                                if (res.data.status === 1) {
                                    if (this.remember) {
                                        //记住帐号密码
                                        this.$cache.addRememberAccount(`${this.form.username},${this.form.password}`);
                                    } else {
                                        this.$cache.removeRememberAccount();
                                    }
                                    setToken(res.data.token);
                                    setAccount(this.form.username);
                                    console.log(res);
                                    this.$router.push({ name: 'check' });
                                    // this._queryUser();
                                    // sessionStorage.setItem('user', JSON.stringify({account_number: data.account_number, account_id: data.account_id}));
                                } else {
                                    this.errortitle = res.data.desc;
                                    this.loading = false;
                                }
                            }
                        })
                        .catch((error) => {});

                    // this.$http
                    //     .post(this.$api.login.POST_LOGIN, this.loginForm)
                    //     .then(
                    //         (resolve) => {
                    //             if (!(resolve && resolve.data)) {
                    //                 return;
                    //             }
                    //             if (this.remember) {
                    //                 //记住密码
                    //                 this.$cache.addRememberToken(`${this.loginForm.account},${this.loginForm.password}`);
                    //             } else {
                    //                 this.$cache.removeRememberToken();
                    //             }

                    //             this.$cache.addUserInfo(resolve.data);
                    //             this.setUserStore(resolve.data);

                    //             let targetPath = '/location'; //登录后默认跳转到定位页面
                    //             if (this.$route.query.redirect && this.$route.query.redirect !== '/') {
                    //                 targetPath = this.$route.query.redirect; //如果有重定向参数且不是根路由，登录后跳转到重定向指定路由
                    //             }

                    //             this.$router.push(targetPath);
                    //         },
                    //         (reject) => {
                    //             console.log(reject);
                    //         }
                    //     )
                    //     .then((val) => {
                    //         this.loging = false;
                    //     });
                } else {
                    return false;
                }
            });
        },
        /**
         * 记住密码复选框切换事件
         * @param {String} val 切换后的状态值
         */
        rememberChange(val) {
            if (!val) {
                this.$cache.removeRememberAccount();
            }
        },

        /**
         * 填充帐号和密码
         */
        fillAccounPassword() {
            if (window.localStorage.remember === 'true' && window.localStorage.account) {
                this.remember = true;
                const account = secret.decrypt(window.localStorage.account);
                const accountArr = account.split(',');
                this.form.username = accountArr[0];
                this.form.password = accountArr[1];
            }
        },
    },
};
</script>

<style lang="scss" scoped>
.login {
    height: 100%;
    background-color: #0f0159;
    .login-logo {
        display: flex;
        // flex-direction: column;
        align-items: center;
        position: absolute;
        z-index: 2;
        left: 68px;
        top: 55px;
        & > img {
            width: 196px;
            height: 51px;
        }
        .line {
            width: 1px;
            height: 29px;
            background: rgba(255, 255, 255, 1);
            opacity: 0.5;
            margin: 0 34px;
        }
        .title {
            font-size: 32px;
            font-family: Microsoft YaHei;
            font-weight: bold;
            letter-spacing: 4px;
            color: rgba(255, 255, 255, 1);
        }
        // margin-top: 0;
        // text-align: center;
    }
    .right {
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        z-index: 2;
        right: 0;
        width: 50%;
        height: 100%;
        background: #fff;
        box-shadow: 0 0 10px rgba(255, 255, 255, 0.6);
    }
    .login-form {
        width: 320px;
        padding: 20px;
        border-radius: 4px;
        background-color: #fff;
        .login-title {
            width: 70px;
            font-size: 16px;
            font-family: Microsoft YaHei;
            font-weight: bold;
            color: rgba(26, 26, 26, 1);
            padding: 10px 0;
            border-bottom: 2px solid #4f82fa;
            margin-bottom: 34px;
        }
        .btn {
            margin-top: 40px;
        }
        .el-input,
        .el-button {
            width: 100%;
            height: 40px;
        }
        .el-input {
            /deep/input {
                height: 40px;
            }
        }

        .item-gap-min {
            margin-bottom: 8px;
        }

        .el-checkbox {
            margin-right: 70px;
        }
    }

    .login-bg {
        position: fixed;
        width: 80%;
        height: 100%;
        left: 0;
        top: 0;
        background: url(~@/assets/images/bj.png) repeat-x center center;
        background-size: cover;
    }
    .errortitle {
        text-align: center;
        color: red;
    }
}
</style>
