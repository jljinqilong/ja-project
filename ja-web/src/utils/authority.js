// use localStorage to store the authority info, which might be sent from server in actual project.
import store from '../index';

export function setAuthority(authority) {
  return localStorage.setItem('antd-pro-authority', authority);
}

export function setToken(token) {
  return localStorage.setItem('security.token', token);
}

export function getToken() {
  const token = localStorage.getItem('security.token');
  if (!token) {
    return null;
  }
  return token;
}

export function setTokenExpired() {
  localStorage.removeItem('security.token');
}

export function setAuths(auths) {
  return localStorage.setItem('security.auths', JSON.stringify(auths));
}

export function getAuths() {
  return JSON.parse(localStorage.getItem('security.auths'));
}

export function removeAuths() {
  localStorage.removeItem('security.auths');
}

export function hasAccessUrl(access) {
  const auths = getAuths();
  if (auths != null && auths.length > 0) {
    for (let i = 0; i < auths.length; i++) {
      let auth = auths[i];
      if (auth.accessUrl !== undefined && auth.accessUrl === access) {
        return true;
      }
    }
  }
  return false;
}

/**
 * 根据key匹配权限
 * @param access
 * @returns {boolean}
 */
export function hasAccessKey(access) {
  const auths = getAuths();
  if (auths != null && auths.length > 0) {
    for (let i = 0; i < auths.length; i++) {
      let auth = auths[i];
      if (auth.key !== undefined && auth.key === access) {
        return true;
      }
    }
  }
  return false;
}

/**
 * 判断是否有权限
 * @param {string} [authorityIdentity]
 */
export function hasPermission(authorityIdentity) {
  return true;
}

/**
 * 切换语言
 * @param lang
 */
export function saveLang(lang) {
  return localStorage.setItem('security.lang', lang);
}

/**
 * 获取当前语言
 * @returns {string | null}
 */
export function getLang() {
  let lang = localStorage.getItem('security.lang');
  if (lang == null || lang === undefined) {
    lang = 'zh_CN';
  }
  return lang;
}
