import axios from 'axios'
import config from '../../config'

const instance = axios.create({
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 30000,
  baseURL: config.dev.baseUrl
})

instance.interceptors.request.use(config => {
  return config
}, error => {
  console.log(error)
  return Promise.reject(error);
})

instance.interceptors.response.use(response => {
  return response;
}, error => {
  console.log('err' + error)
  return Promise.reject(error);
});

class HttpUtil {
  get(url, data = {}) {
    return new Promise((resolve, reject) => {
      instance.get(url, {
        params: data
      })
        .then(response => {
          resolve(response);
        })
        .catch(err => {
          reject(err)
        })
    })
  }

  post(url, data = {}) {
    return new Promise((resolve, reject) => {
      instance.post(url, data).then(response => {
        resolve(response.data);
      }, err => {
        reject(err);
      })
    })
  }

  del(url, data = {}) {
    return new Promise((resolve, reject) => {
      instance.delete(url, {
        params: data
      })
        .then(response => {
          resolve(response);
        })
        .catch(err => {
          reject(err)
        })
    })
  }

  put(url, data = {}) {
    return new Promise((resolve, reject) => {
      instance.put(url, {
        params: data
      })
        .then(response => {
          resolve(response);
        })
        .catch(err => {
          reject(err)
        })
    })
  }

  request(methed, url, data = {}, headers) {
    return new Promise((resolve, reject) => {
      instance({
        method: methed || 'post',
        url: url,
        params: methed === 'get' ? data : '',
        data: methed !== 'get' ? data : '',
        headers: headers || {'Content-Type': 'application/json'},
      })
        .then(response => {
          resolve(response.data);
        })
        .catch(err => {
          reject(err)
        })
    })
  }
}

let httpUtil = new HttpUtil();
export default httpUtil
