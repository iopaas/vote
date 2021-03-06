import Cookies from "js-cookie";

const TokenKey = "Admin-Token";
const AccountKey = "Account";

export function getToken() {
  return Cookies.get(TokenKey);
}

export function setToken(token) {
  return Cookies.set(TokenKey, token);
}

export function removeToken() {
  return Cookies.remove(TokenKey);
}

export function getAccount() {
  return Cookies.get(AccountKey);
}

export function setAccount(account) {
  return Cookies.set(AccountKey, account);
}

export function removeAccount() {
  return Cookies.remove(AccountKey);
}
