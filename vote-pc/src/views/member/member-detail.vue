<template>
    <div>
        <el-dialog
            :title="title"
            :visible.sync="visible"
            @close="$emit('update:show', false)"
            :show="show">
            <div class="amform">
             <el-form ref="form" :model="form" :rules="rules" label-width="90px">
                <el-form-item label="名字/英文" prop="candidateName">
                    <el-input v-model="form.candidateName" maxlength="50"></el-input>
                </el-form-item>
                <el-form-item label="身份证号" prop="candidateIdCard">
                    <el-input v-model="form.candidateIdCard" maxlength="20"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱" prop="candidateEmail">
                    <el-input v-model="form.candidateEmail" maxlength="50"></el-input>
                </el-form-item>

                <el-form-item label="状态">
                    <el-radio-group v-model="form.status">
                        <el-radio :label="1">有效</el-radio>
                        <el-radio :label="2">无效</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item style="text-algin:center">
                    <el-button type="success" plain @click="_addMember('form')" style="margin-left:150px;width:100px;">确认</el-button>
                    <el-button plain @click="closeDialog(0)" style="margin-left:50px;width:100px;">取消</el-button>
                </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { queryMember, addMember, updateMember} from '@/api/index';
import { getToken } from '@/utils/auth';
import axios from 'axios';
import { format } from 'date-fns';
export default {
    data() {
        var checkIdCard = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入正确的身份证号!"));
            } else if (!this.isIdCard(value)) {
                callback(new Error("请输入正确的身份证号!"));
            } else {
                callback();
            }
        };

        var checkEmail = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入正确的邮箱!"));
            } else if (!this.isEmail(value)) {
                callback(new Error("请输入正确的邮箱!"));
            } else {
                callback();
            }
        };
        return {
            visible: this.show,
            form: {
                id: 0,
                candidatePhoto:'',
                candidateName: '',
                candidateIdCard: '',
                candidateEmail: '',
                status: 1,
            },
            rules: {
                candidateName: [
                    { required: true, message: '请填写!',  trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 3 到 50 个字符!', trigger: 'blur' }
                ],
                candidateIdCard: [
                    { required: true, validator: checkIdCard, trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 6 到 20 个字符!', trigger: 'blur' }
                ],
                candidateEmail: [
                    { required: true, validator: checkEmail, trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 8 到 100 个字符!', trigger: 'blur' }
            ],
            },

        };
    },
    props: {
        show: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: '弹出框'
        },
        pkId: {
            type: Number,
            default: 0
        },
        fatherMethod: {
            type: Function,
            default: null
       }
    },
    watch: {
        show () {
            this.visible = this.show;
            this.form.id = this.pkId;
            if(this.visible === true && this.pkId != 0){
              this._queryMember();
            }
        }
    },
    created() {
        
    },
    mounted() {
        
    },
    methods: {
        /**
         * 用户设置对话框指令
         * @param {String} command 指令值
         */
        // 取消对话框
        closeDialog(id) {
            this.form.id = id;
            this.$emit('update:show', false);
        },

        goAfterMethod(isUpate) {
            this.closeDialog(0);
            if (this.fatherMethod) {
                this.fatherMethod(isUpate);
            }
           
        },

        isIdCard(val){
           const regCard = /^[A-Z]\d{6}\([\dA]\)$/;
            if (!regCard.test(val)) {
               return false;
            }
            return true;
        },

        isEmail(val){
            const regEmail = /^([A-Za-z0-9_-|\.])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
            if (!regEmail.test(val)) {
                return false;
            }
            return true;
        },
  
        /**
         * 获取候选人
         * 
         */
        _queryMember() {
            let params = {
                id: this.form.id,
            };
            queryMember(params).then((res) => {
                if (res.code && res.code === 1) {
                    this.form = res.data;
                    
                }
            });
        },
        /**
         * 保存候选人
         */
        _addMember(formName) {

            this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params =  this.form;
                        if(params.candidatePhoto === ''){
                           params.candidatePhoto = 'http://47.107.144.13:8008/img/touxiang.7ce7e6ae.png';
                        }

                        if(params.id ==0){
                            addMember(params).then((res) => {
                                if (res.code && res.code === 1) {
                                    this.form.id = res.data.param;
                                    this.goAfterMethod(false);
                                }
                            });   
                        }else{
                            updateMember(params).then((res) => {
                                if (res.code && res.code === 1) {
                                   this.goAfterMethod(true);
                                }
                            });
                        } 
                        
                    } else {
                        return false;
                    }
            });
            
                
        },
    },
};
</script>
<style lang="scss" scoped>
.box-card {
    border-width: 0;
    border-radius: 2px;
    .card-title {
        line-height: 32px;
    }
}

.search-card {
    padding: 20px;
    margin-bottom: 20px;
}

.operational {
    * + * {
        margin-left: 15px;
    }
}
.pagination {
    padding: 12px 20px;
}
::v-deep {
    .el-card__header {
        border-bottom-color: #f0f0f0;
    }

    .el-table th {
        padding: 12px 0;
        background-color: #fafafa;
    }

    .el-dropdown-menu {
        min-width: 150px;
    }
    .el-form--inline .el-form-item {
        vertical-align: bottom;
    }
}
.demo-form-inline {
    .el-form-item {
        margin-bottom: 20px;
    }
}
.table-foot {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>
