import httpUtil from '../utils/http'

const base_uri = 'scenic-dtl/'

class ScenicDtl {
  findById(data = {}) {
    return httpUtil.get(base_uri + "findById", data);
  }
}

let scenicDtl = new ScenicDtl();
export default scenicDtl;
