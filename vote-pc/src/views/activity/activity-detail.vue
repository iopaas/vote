<template>
    <div>
        <el-dialog
            :title="title"
            :visible.sync="visible"
            @close="$emit('update:show', false)"
            :show="show">
            <div class="adform">
             <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="主题名称" prop="activityTitle">
                    <el-input v-model="form.activityTitle"></el-input>
                </el-form-item>
                <el-form-item label="主题描述" prop="activityDesc">
                    <el-input type="textarea" v-model="form.activityDesc"></el-input>
                </el-form-item>
                
                <el-form-item label="投票时间" prop="times">
                    <el-date-picker
                        v-model="form.times"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        value-format="yyyy-MM-dd"
                        :picker-options="pickerOptions"
                    >
                    </el-date-picker>
                </el-form-item>

               <el-form-item label="候选人员" prop="mermbers">
                    <span v-for="(item, index) in form.actMemberList" :key="index" style="line-height:40px; width:300px;display: inline-block;">
                        <el-checkbox v-model="item.checked"  style="vertical-align: 0px;" :label="item.id" :checked="item.existFlag==1" @change="chooseItem(item.memberId)">
                            <img :src="item.candidatePhoto" class="g"  width="30px" height="30px" style="vertical-align: -10px;">
                            <span style="margin-left:5px;">{{item.candidateName}}</span>
                        </el-checkbox>
                    </span>
                </el-form-item>

                <el-form-item label="活动状态">
                    <el-radio-group v-model="form.status">
                        <el-radio :label="0">未开启</el-radio>
                        <el-radio :label="1">开启中</el-radio>
                        <el-radio :label="2">结束</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item style="text-algin:center">
                    <el-button type="success" plain @click="_addActivity('form')" style="margin-left:150px;width:100px;">确认</el-button>
                    <el-button plain @click="closeDialog(0)" style="margin-left:50px;width:100px;">取消</el-button>
                </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { queryActivity, addActivity, updateActivity} from '@/api/index';
import { getToken } from '@/utils/auth';
import axios from 'axios';
import { format } from 'date-fns';
export default {

    data() {

        var checkTimes = (rule, value, callback) => {
            if (!this.form.times || (this.form.times.length < 2)) {
                callback(new Error('请选择投票开始时间和结束时间'));
            } else {
                callback();
            }
        };

        var checkMermbers = (rule, value, callback) => {
            if (this.form.memberIds.length < 2) {
                callback(new Error('请选择至少2名候选人'));
            } else {
                callback();
            }
        };

        return {
            visible: this.show,
            statusList: [
                { value: 0, label: '未开启' },
                { value: 1, label: '开启中' },
                { value: 2, label: '已结束' },
            ],
            form: {
                id: 0,
                activityTitle: '',
                activityDesc: '',
                startTime: '',
                endTime: '',
                status: 0,
                times: [],
                memberIds: [],
                actMemberList: [],
            },
            rules: {
                activityTitle: [
                    { required: true, message: '请填写', trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                ],
                activityDesc: [
                    { required: true, message: '请填写', trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 10 到 500 个字符', trigger: 'blur' }
                ],
                times: [
                    { required: false, validator: checkTimes, message: '请选择投票开始时间和结束时间', trigger: 'blur' }
                ],
                mermbers: [
                    { required: false, validator: checkMermbers, message: '请选择至少2名候选人', trigger: 'blur' }
                ]
            },
            pickerOptions: {
                disabledDate(time) {
                    return (Date.now() - 3600 * 1000 * 24) >= time.getTime();
                },
            }
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
            if(this.visible === true){
              this._queryActivity();
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
            this.form.actMemberList = [];
            this.$emit('update:show', false);
        },
        
        goAfterMethod(isUpate) {
            this.closeDialog(0);
            if (this.fatherMethod) {
                this.fatherMethod(isUpate);
            }
        },

        chooseItem(id) {
            let arr = this.form.memberIds;
            if(arr.indexOf(id) === -1){
               arr.push(id);
            }else{
               arr.splice(arr.indexOf(id), 1); 
            }
            this.form.memberIds = arr;
        },

        /**
         * 获取候选人列表
         * 
         */
        _queryActivity() {
            let params = {
                id: this.form.id,
            };
            queryActivity(params).then((res) => {
                if (res.code && res.code === 1) {
                    this.form = res.data;
                    if(this.form.startTime && this.form.endTime){
                      this.form.times = [this.form.startTime.substring(0,10), this.form.endTime.substring(0,10)];
                    }
                    if(params.id === 0){
                      this.form.status = 0;
                    }
                }
            });
        },
        /**
         * 提交投票
         */
        _addActivity(formName) {

            this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params =  this.form;
                        if(this.form.times){
                          params.startTime = params.times[0] +' 00:00:00';
                          params.endTime = params.times[1] +' 23:59:59';
                        }
                        params.actMemberList = [];
                        
                        if(params.id === 0){
                            addActivity(params).then((res) => {
                                if (res.code && res.code === 1) {
                                    this.form.id = res.data.param;
                                    this.goAfterMethod(false);
                                }
                            });   
                        }else{
                            updateActivity(params).then((res) => {
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
