import httpUtil from '../utils/http'

const base_uri = 'go-with/'

class GoWithApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
  save(data = {}){
    return httpUtil.post(base_uri + "save", data);
  }
  findById(data = {}){
    return httpUtil.get(base_uri + "findById",data);
  }
  hotGoWithList(){
    return httpUtil.get(base_uri + "hotGoWithList");
  }
  update(data = {}){
    return httpUtil.post(base_uri + "update",data);
  }
  findByUserId(data = {}){
    return httpUtil.get(base_uri + "findByUserId",data);
  }
}

let goWithApi = new GoWithApi();
export default goWithApi;
