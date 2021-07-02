import request from "@/utils/request";

// 登录
export function accountLogin(data) {
  return request({
    url: "/api/plat/comm/accountLogin",
    method: "post",
    data,
  });
}

// 查询候选人列表（创建投票活动）
export function queryMemberList(data) {
  return request({
    url: "/api/plat/activity/queryMemberList",
    method: "get",
    params: data,
  });
}

// 查询候选人
export function queryMember(data) {
  return request({
    url: "/api/plat/member/query",
    method: "get",
    params: data,
  });
}
// 新增候选人
export function addMember(data) {
  return request({
    url: "/api/plat/member/add",
    method: "post",
    data,
  });
}

// 编辑候选人
export function updateMember(data) {
  return request({
    url: "/api/plat/member/update",
    method: "put",
    data,
  });
}

// 保存投票活动
export function addActivity(data) {
  return request({
    url: "/api/plat/activity/add",
    method: "post",
    data,
  });
}

// 修改投票活动
export function updateActivity(data) {
  return request({
    url: "/api/plat/activity/update",
    method: "put",
    data,
  });
}

// 查询投票活动列表
export function queryActivityListPage(data) {
  return request({
    url: "/api/plat/activity/queryListForPage",
    method: "post",
    params: data,
  });
}

// 查询投票活动
export function queryActivity(data) {
  return request({
    url: "/api/plat/activity/query",
    method: "get",
    params: data,
  });
}

// 查询用户投票列表
export function queryActivityVoteListPage(data) {
  return request({
    url: "/api/plat/activity/queryVoteListForPage",
    method: "post",
    params: data,
  });
}

// 查询被投票人列表
export function queryMemberListPage(data) {
  return request({
    url: "/api/plat/member/queryListForPage",
    method: "post",
    data,
  });
}



