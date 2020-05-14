import httpUtil from '../utils/http'

const base_uri = 'hotel/'

class HotelApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }
}

let hotelApi = new HotelApi();
export default hotelApi;
