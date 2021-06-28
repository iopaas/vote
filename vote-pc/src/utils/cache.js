import secret from '@/utils/secret';

/**
 * 添加记住标记及帐号密码到本地缓存
 * @param {String} val 帐号密码信息
 */
export function addRememberAccount(val) {
    window.localStorage.setItem('remember', 'true');
    window.localStorage.setItem('account', secret.encrypt(val));
}

/**
 * 清除本地缓存中的记住帐号标记及token
 */
export function removeRememberAccount() {
    window.localStorage.removeItem('remember');
    window.localStorage.removeItem('account');
}

/**
 * 缓存用户信息
 * @param {Object} data 用户信息
 */
export function addUserInfo(data) {
    window.localStorage.setItem('userInfo', JSON.stringify(data));
}

/**
 * 清除所有vuex缓存
 * @param {Object} data 用户信息
 */
export function removeVuexInfo() {
    window.sessionStorage.removeItem('vuex');
}

/**
 * 清除用户信息缓存
 */
export function removeUserInfo() {
    window.localStorage.removeItem('userInfo');
}

/**
 * 获取用户信息
 */
export function getUserInfo() {
    let userInfo = window.localStorage.getItem('userInfo');
    if (userInfo) {
        return JSON.parse(userInfo);
    }
    return userInfo;
}

export default {
    addRememberAccount,
    removeRememberAccount,
    addUserInfo,
    removeUserInfo,
    getUserInfo,
};
