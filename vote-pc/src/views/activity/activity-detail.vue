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
                    >
                    </el-date-picker>
                </el-form-item>
       
                <el-form-item label="是否开启">
                    <el-switch v-model="state"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        :active-value="1"
                        :inactive-value="0"
                        @change="changeSwitch($event,form.status)">
                    </el-switch>
                </el-form-item>

               <el-form-item label="候选人员" prop="mermbers">
                    <li v-for="(item, index) in form.actMemberList" :key="index" style="line-height:40px;">
                        <el-checkbox v-model="item.checked"  style="vertical-align: 0px;" :checked="item.existFlag==1" :label="item.id" @change="chooseItem(item.memberId)">
                            <img :src="item.candidatePhoto" class="g"  width="30px" height="30px" style="vertical-align: -10px;">
                            <span style="margin-left:5px;">{{item.candidateName}}</span>
                        </el-checkbox>
                    </li>
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
        return {
            visible: this.show,
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
                    { required: true, message: '主题名称', trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                ],
                activityDesc: [
                    { required: true, message: '主题描述', trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 10 到 500 个字符', trigger: 'blur' }
                ],
                mermbers: [
                    { required: false, message: '选中候选人', trigger: 'blur' }
                ]
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
        }
    },
    watch: {
        show () {
            this.visible = this.show;
            this.form.id = this.pkId;
            this.title = this.pkId == 0 ? '创建投票活动' : '编辑投票活动';
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
        // 取消
        closeDialog(id) {
            this.show = false;
            this.form.id = id;
            this.$emit('closepop');
        },
        changeSwitch(e,value){
            this.form.status = value === 1 ? 1: 0;
        },
        chooseItem(val) {
            if(this.form.memberIds.indexOf(val)){ 
                var index = this.form.memberIds.findIndex(item =>{
　　　　　　　　　　if(item==val){
　　　　　　　　　　　　return true
　　　　　　　　　　}
　　　　　　　　})
              if(index < 0){
                this.form.memberIds.push(val);
              }else{
                this.form.memberIds.splice(index, 1);
              }
            } 
            
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
                        params.status = 1;
                        params.actMemberList = [];
                        
                        if(params.id ==0){
                            addActivity(params).then((res) => {
                                if (res.code && res.code === 1) {
                                    this.form.id = res.data.param;
                                    this.childMethod();
                                }
                            });   
                        }else{
                            updateActivity(params).then((res) => {
                                if (res.code && res.code === 1) {
                                   this.childMethod();
                                }
                            });
                        } 
                        
                        this.show = false;
                        this.form.id = 0;
                    } else {
                        return false;
                    }
            });
            
                
        },
        childMethod() {
            this.$parent.fatherMethod('search');
        }
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
