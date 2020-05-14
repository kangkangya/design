import httpUtil from '../utils/http'

const base_uri = 'region/'

class RegionApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
}

let regionApi = new RegionApi();
export default regionApi;
