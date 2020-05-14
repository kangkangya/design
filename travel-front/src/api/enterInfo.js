import httpUtil from '../utils/http'

const base_uri = 'enterInfo/';

class EnterInfoApi {

  save(data = {}){
    return httpUtil.post(base_uri + "save", data);
  }

  findByUserId(data = {}){
    return httpUtil.get(base_uri + "findByUserId",data);
  }

  delete(data = {}){
    return httpUtil.get(base_uri + "delete",data);
  }
}

let enterInfoApi = new EnterInfoApi();
export default enterInfoApi;
