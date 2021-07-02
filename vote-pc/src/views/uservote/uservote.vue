<template>
    <div class="check">
        <h5 class="title" style="margin-bottom: 15px;">
            投票信息
        </h5>
        <el-card class="box-card search-card" shadow="never" :body-style="{ padding: 0 }">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item>
                    <el-link type="primary">{{activityName}}</el-link>
                </el-form-item>
            </el-form>
        </el-card>
        <h5 class="title" style="margin: 15px 0;">
            投票用户
            <el-button type="primary" @click="goBack()" icon="el-icon-arrow-left" style="margin-left:20px;">返回</el-button>
        </h5>
        <el-card class="box-card" shadow="never" :body-style="{ padding: 0 }">
            <el-table class="w-100" :data="tableData" default-expand-all :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
                <el-table-column label="序号"  width="70px">
                    <template slot-scope="scope">
                        {{scope.$index+1}}
                    </template>
                </el-table-column>
                <!--
                <el-table-column prop="userName" label="名字/英文"> </el-table-column>-->
                <el-table-column prop="idCard" label="身份证号" width="120px"> </el-table-column>
                <el-table-column prop="email" label="电子邮箱" > </el-table-column>
                <el-table-column prop="createTime" label="投票时间"> </el-table-column>
            </el-table>
            <el-pagination
                class="pagination"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tablePagination.total"
                :page-size="tablePagination.pageSize"
                :current-page="tablePagination.currentPage"
                :page-sizes="[5, 10, 50, 100]"
                @size-change="tableSizeChange"
                @current-change="tablePaginationChange"
            ></el-pagination>
        </el-card>
    </div>
</template>

<script>
import {  queryActivityVoteListPage } from '@/api/index';
import { getToken, getAccount } from '@/utils/auth';
import axios from 'axios';
import { format } from 'date-fns';
export default {
    data() {
        return {
            activityId: 0,
            activityName: '',
            // 状态列表
            statusList: [
                { value: 0, label: '全部' },
                { value: 1, label: '有效' },
                { value: 2, label: '无效' },
            ],
            // 切换分页等操作是否带上搜索条件
            copySearchForm: {
                userName: '',
                idCard: '',
                email: '',
            },
            searchForm: {
                activityId: 0,
                userName: '',
                idCard: '',
                email: '',
            },
            //表格数据
            tableData: [
                {
                    id: 1, 
                    userName: '', 
                    idCard: '',
                    email: '',
                    status: 0, 
                    createTime: '', 
                },
            ],
            // 分页
            tablePagination: {
                total: 0, //总条目数
                pageSize: 10, //每页显示条目个数
                pageNum: 1, //当前页
            },
        };
    },
    created() {
        
    },
    mounted() {
        this.activityId = this.$route.query.activityId;
        this.activityName = this.$route.query.activityName;
        this.getTableData({
            pageNum: 1,
        });
    },
    methods: {
        /**
         * 用户设置对话框指令
         * @param {String} command 指令值
         */
        // userSettingCommand(command) {
        //     switch (command) {
        //         case 'logout':
        //             this.signOut();
        //             break;
        //         default:
        //             break;
        //     }
        // },

        // 查询
        search() {
            this.copySearchForm = JSON.parse(JSON.stringify(this.searchForm));
            this.getTableData({
                pageNum: 1,
            });
        },
        /**
         * 获取表单数据
         */
        getTableData(opts) {
            let params = {
                ...opts,
                ...this.copySearchForm,
                pageSize: this.tablePagination.pageSize,
                activityId: this.activityId,
            };
            if (params.times && params.times.length) {
                params.startTime = params.times[0];
                params.endTime = params.times[1];
            } else {
                params.startTime = '';
                params.endTime = '';
            }

            queryActivityVoteListPage(params).then((res) => {
                if (res.code && res.code === 1) {
                    this.tableData = res.data.list;
                    this.tablePagination.total = res.data.total;
                    this.tablePagination.pageSize = res.data.pageSize;
                    this.tablePagination.pageNo = res.data.pageNum;
                }
            });
        },
        /**
         * 表格分页改变
         */
        tablePaginationChange(page) {
            this.getTableData({
                pageNum: page,
            });
        },
        /**
         * 表格每页显示条目个数改变
         */
        tableSizeChange(pageSize) {
            this.tablePagination.pageSize = pageSize;
            this.getTableData({
                pageNum: 1,
            });
        },

        goBack(){
          this.$router.go(-1);//返回上一层
        },
    },
};
</script>
<style lang="scss" scoped>
.box-card {
    border-width: 0;
    border-radius: 2px;
    .card-title {
        line-height: 26px;
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
