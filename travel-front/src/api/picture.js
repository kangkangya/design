import httpUtil from '../utils/http'

const base_uri = 'picture/'

class Picture {
  upload(action, data = {}) {
    let headers = {
      headers: {'Content-Type': 'multipart/form-data'}
    };
    return httpUtil.request('post', base_uri + action, data, headers);
  }

  del(data = {}) {
    return httpUtil.del(base_uri + "delete", data);
  }
}

let picture = new Picture();
export default picture;
