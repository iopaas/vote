<template>
    <div class="check">
        <h5 class="title" style="margin-bottom: 15px;">
            活动查询
        </h5>
        <el-card class="box-card search-card" shadow="never" :body-style="{ padding: 0 }">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="投票主题">
                    <el-input v-model="searchForm.activityTitle" placeholder="" style="width:150px"></el-input>
                </el-form-item>

                <el-form-item label="投票内容">
                    <el-input v-model="searchForm.activityDesc" placeholder=""  style="width:150px"></el-input>
                </el-form-item>

                <el-form-item label="创建时间">
                    <el-date-picker
                        v-model="searchForm.times"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        value-format="yyyy-MM-dd"
                    >
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
                </el-form-item>

            </el-form>
        </el-card>
        <h5 class="title" style="margin: 15px 0;">
            活动列表
            <el-button type="success" @click="openDialog(0)" icon="el-icon-edit" style="margin-left:10px;">发起投票活动</el-button>
        </h5>

        <el-card class="box-card" shadow="never" :body-style="{ padding: 0 }">
            <el-table class="w-100" :data="tableData" default-expand-all :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
                <el-table-column label="序号"  width="70px">
                    <template slot-scope="scope">
                        {{scope.$index+1}}
                    </template>
                </el-table-column>
                <el-table-column prop="activityTitle" label="投票主题" width="160"> </el-table-column>
                <el-table-column prop="activityDesc" label="投票内容" width="300"> </el-table-column>
                <el-table-column prop="startTime" label="投票时段" width="138" >
                    <template slot-scope="scope">
                        {{scope.row.startTime}}{{scope.row.endTime}}
                    </template>    
                </el-table-column>
                <el-table-column prop="enabled" label="状态" width="80" >
                     <template slot-scope="scope">
                        {{ scope.row.enabled === 0 ? '不可投' : scope.row.enabled === 1 ? '可投' : '' }}
                    </template>
                </el-table-column>
                <el-table-column label="投票统计"  width="300">
                    <el-progress :text-inside="true" :stroke-width="20" :percentage="50"></el-progress>
                    <template slot-scope="scope">
						<div v-for="(item, index) in tableData[scope.$index].actMemberList" :key="index" style="line-height:40px;">
							<img :src="scope.row.actMemberList[index].candidatePhoto" width="30px" height="30px">
                            <span style="vertical-align: 10px;margin-left:5px;">{{scope.row.actMemberList[index].candidateName}}
                                 <span style="margin-lft:10px;color:red;">{{scope.row.actMemberList[index].votes}} 票</span>
                                <el-progress :text-inside="true" :stroke-width="22" :percentage="scope.row.actMemberList[index].votes" :format="format" ></el-progress>
                            </span>
                            
                        </div>
					</template>
                </el-table-column>
                <el-table-column label="投票用户" width="70">
                    <template slot-scope="scope">
                        <el-button type="text" @click="goVoteUser(scope.row.id, scope.row.activityTitle, scope.row.activityDesc)"  size="small">查看</el-button>
                    </template>
                </el-table-column>
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
        </el-card>

        <service-dialog :show.sync="show" :pkId="pkId" />
    </div>
</template>

<script>
import { queryActivityListPage } from '@/api/index';
import serviceDialog  from "./activity-detail.vue";
import axios from 'axios';
import { format } from 'date-fns';
export default {
    components: {
        serviceDialog
    },
    data() {
        return {
            show: false,
            pkId: 0,
            // 切换分页等操作是否带上搜索条件
            copySearchForm: {
                activityTitle: '',
                activityDesc: '',
                times: [],
            },
            searchForm: {
                activityTitle: '',
                activityDesc: '',
                times: [],
            },
            //表格数据
            tableData: [
                {
                    id: '1', // 预约ID
                    empactivityTitle: '', // 操作人
                    startTime: '2021-06-25', // 预约日期
                    endTime: '2021-06-25', // 预约时间
                },
            ],
            // 分页
            tablePagination: {
                total: 0, //总条目数
                pageSize: 5, //每页显示条目个数
                pageNum: 1, //当前页
            },
        };
    },
    created() {
    
    },
    mounted() {
        this.getTableData({
            pageNum: 1,
        });

        // this.serviceDialog.$on('query',() => {
        //     console.log('响应child1的点击事件');
        // })
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
        fatherMethod(str) {
           this.getTableData({
                pageNum: 1,
            });
        },
        openDialog(id) {
            this.show = true;
            this.pkId = id;
        } ,
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

            queryActivityListPage(params).then((res) => {
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
        format(percentage) {
           return percentage === 100 ? 100 : `${percentage}`;
        },

        goVoteUser(activityId, activityTitle,activityDesc){
            this.$router.push({
                name: 'uservote',
                query: {
                   activityId: activityId,
                   activityName: activityTitle + activityDesc,
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
.el-progress-bar__innerText {
    display: inline-block;
    vertical-align: middle;
    color: red;
    font-size: 12px;
    margin: 0 5px;
}
</style>
