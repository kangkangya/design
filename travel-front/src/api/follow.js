import httpUtil from "../utils/http";

const base_uri = 'follow/';

class FollowApi {
  save(data = {}){
    return httpUtil.post(base_uri + 'save',data);
  }

  findOne(data = {}){
    return httpUtil.post(base_uri + 'findOne',data);
  }

  delete(data = {}){
    return httpUtil.get(base_uri + 'delete',data);
  }
}

let followApi = new FollowApi();
export default followApi;
