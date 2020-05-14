import httpUtil from '../utils/http'

const base_uri = 'scenic/'

class ScenicApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
}

let scenicApi = new ScenicApi();
export default scenicApi;
