<template>
    <div class="check">
        <h5 class="title" style="margin-bottom: 15px;">
            候选人查询
        </h5>
        <el-card class="box-card search-card" shadow="never" :body-style="{ padding: 0 }">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="名字/英文">
                    <el-input v-model="searchForm.candidateName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input v-model="searchForm.candidateIdCard" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱">
                    <el-input v-model="searchForm.candidateEmail" placeholder=""></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <h5 class="title" style="margin: 15px 0;">
            候选人列表
            <el-button type="success" @click="openDialog(0)" icon="el-icon-edit" style="margin-left:20px;">新增候选人</el-button>
        </h5>
        <el-card class="box-card" shadow="never" :body-style="{ padding: 0 }">
            <el-table class="w-100" :data="tableData" default-expand-all :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
                <el-table-column label="序号"  width="70px">
                    <template slot-scope="scope">
                        {{scope.$index+1}}
                    </template>
                </el-table-column>
                <el-table-column prop="candidatePhoto" label="头像"  width="120"> 
                <template slot-scope="scope" v-if="scope.row.candidatePhoto != ''">
                    <img :src="scope.row.candidatePhoto" width="50px" height="50px">
                </template>
                </el-table-column>
                <el-table-column prop="candidateName" label="名字/英文"  width="220px"> </el-table-column>
                <el-table-column prop="candidateIdCard" label="身份证号" width="120px"> </el-table-column>
                <el-table-column prop="candidateEmail" label="电子邮箱" > </el-table-column>
                <el-table-column prop="status" label="状态" width="80px">
                    <template slot-scope="scope" v-if="statusList[scope.row.status]">
                        {{ statusList[scope.row.status].label }}
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间"> </el-table-column>
                 <el-table-column label="编辑" width="70">
                    <template slot-scope="scope">
                        <el-button type="text" @click="openDialog(scope.row.id)"  size="small">编辑</el-button>
                    </template>
                </el-table-column>
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

            <childDialog :show.sync="show" :title="title" :pkId="pkId" :fatherMethod="fatherMethod"></childDialog>
        </el-card>
    </div>
</template>

<script>
import {  queryMemberListPage } from '@/api/index';
import { getToken, getAccount } from '@/utils/auth';
import childDialog  from "./member-detail.vue";
import axios from 'axios';
import { format } from 'date-fns';
export default {
    components: {
        childDialog
    },
    data() {
        return {
            show: false,
            pkId: 0,
            title: '',
            // 状态列表
            statusList: [
                { value: 0, label: '全部' },
                { value: 1, label: '有效' },
                { value: 2, label: '无效' },
            ],
            // 切换分页等操作是否带上搜索条件
            copySearchForm: {
                candidateName: '',
                candidateIdCard: '',
                candidateEmail: '',
            },
            searchForm: {
                candidateName: '',
                candidateIdCard: '',
                candidateEmail: '',
            },
            //表格数据
            tableData: [
                {
                    id: 1, 
                    candidatePhoto: '', 
                    candidateName: '',
                    candidateIdCard: '',
                    candidateEmail: '', 
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
       this.fatherMethod(false);
    },
    methods: {
        
        openDialog(id) {
            this.pkId = id;
            this.title = (this.pkId === 0) ? '创建候选人' : '编辑候选人';
            this.show = true;
        } ,

        fatherMethod(isUpate) {
            let pN = 1;
            if(isUpate && this.tablePagination.pageNo){
                pN = this.tablePagination.pageNo;
            }
            this.getTableData({
                pageNum: pN,
            });
        },

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
            };
            if (params.times && params.times.length) {
                params.startTime = params.times[0];
                params.endTime = params.times[1];
            } else {
                params.startTime = '';
                params.endTime = '';
            }
            //this.tablePagination.currentPage = opts.pageNum;

            queryMemberListPage(params).then((res) => {
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
