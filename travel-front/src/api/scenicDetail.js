import httpUtil from "../utils/http";

const base_uri = 'scenic-detail/';

class ScenicDetailApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
}

let scenicDetailApi = new ScenicDetailApi();
export default scenicDetailApi;
