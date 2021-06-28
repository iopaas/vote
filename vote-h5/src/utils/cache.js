// import secret from '@/utils/secret';

/**
 * 添加token记住到本地缓存
 * @param {String} val 帐号密码信息
 */
export function setToken(token) {
  let params = {
    token: token,
  };
  window.localStorage.setItem("token", JSON.stringify(params));
}

/**
 * 获取token
 */
export function getToken() {
  let token = window.localStorage.getItem("token");
  if (token) {
    return JSON.parse(token);
  } else {
    return null;
  }
}

export function removeToken() {
  window.sessionStorage.removeItem("token");
}


export function setRouterParams(param) {
  let params = {
    name: param.name,
    queryId: param.query.id,
  };
  window.localStorage.setItem("routerParams", JSON.stringify(params));
}

export function getRouterParams() {
  let params = window.localStorage.getItem("routerParams");
  if (params) {
    return JSON.parse(params);
  } else {
    return null;
  }
}

export function removeRouterParams() {
  window.sessionStorage.removeItem("routerParams");
}
 
export default {
  setToken,
  removeToken,
  getToken,
  setRouterParams,
  getRouterParams,
  removeRouterParams,
};
