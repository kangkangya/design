import httpUtil from '../utils/http'

const base_uri = 'food-category/'

class FoodCategoryApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
}

let foodCategoryApi = new FoodCategoryApi();
export default foodCategoryApi;
