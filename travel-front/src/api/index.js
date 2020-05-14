import httpUtil from '../utils/http'

const base_uri = 'index/'

class IndexApi {
  bannerList() {
    return httpUtil.get(base_uri + "banner-list");
  }

  scenicList() {
    return httpUtil.get(base_uri + "scenic-list");
  }

  hotelList() {
    return httpUtil.get(base_uri + "hotel-list");
  }
}

let indexApi = new IndexApi();
export default indexApi;
