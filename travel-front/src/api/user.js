import httpUtil from '../utils/http'

const base_uri = 'user/'

class UserApi {
  register(data = {}) {
    return httpUtil.post(base_uri + "register", data);
  }

  login(data = {}) {
    return httpUtil.post(base_uri + "login", data);
  }
}

let userApi = new UserApi();
export default userApi;
