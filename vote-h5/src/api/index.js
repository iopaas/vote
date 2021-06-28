import request from '@/utils/request';
import { getToken } from '@/utils/cache';

const token = getToken();
function addParams(url) {
    return url;
    if (token) {
        return `${url}?token=${token.token}`;
    } else {
        return url;
    }
}


// 用户登录
export function userLogin(data) {
    let url = 'api/plat/apps/user/login';
    return request({
        url: url,
        method: 'post',
        data: data
    });
}


// 查询投票活动列表分页查询(每次10条)
export function queryActivityPage(data) {
    let url = addParams('api/plat/apps/activity/list');
    return request({
        url: url,
        method: 'get',
        params: data,
    });
}

// 查询投票活动详情
export function queryActivityDetail(data) {
    let url = addParams('api/plat/apps/activity/detail');
    return request({
        url: url,
        method: 'get',
        params: data,
    });
}

// 确认投票
export function addUserVote(data) {
    let url = 'api/plat/apps/uservote/add';
    return request({
        url: url,
        method: 'post',
        data: data
    });
}

