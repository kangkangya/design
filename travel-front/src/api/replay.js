import httpUtil from '../utils/http'

const base_uri = 'replay/'

class Replay {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }

  save(data = {}) {
    return httpUtil.post(base_uri + "save", data);
  }
}

let replay = new Replay();
export default replay;
