import httpUtil from '../utils/http'

const base_uri = 'travel-notes/'

class TravelNoteApi {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }

  save(data = {}){
    return httpUtil.post(base_uri + "save", data);
  }
  findByUserId(data = {}){
    return httpUtil.get(base_uri + "findByUserId",data);
  }
}

let travelNoteApi = new TravelNoteApi();
export default travelNoteApi;
