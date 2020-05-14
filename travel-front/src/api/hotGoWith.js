import httpUtil from '../utils/http'

const base_uri = 'hot-go-with/'

class HotGoWithApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
}

let hotGoWithApi = new HotGoWithApi();
export default hotGoWithApi;
