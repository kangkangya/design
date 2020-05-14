import httpUtil from '../utils/http'

const base_uri = 'food/';

class FoodApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
  findById(data={}){
    return httpUtil.get(base_uri + "findById", data);
  }
}

let foodApi = new FoodApi();
export default foodApi;
