import httpUtil from '../utils/http'

const base_uri = 'comment/'

class Comment {
  list(data = {}) {
    return httpUtil.get(base_uri + "list", data);
  }

  save(data = {}) {
    return httpUtil.post(base_uri + "save", data);
  }
}

let comment = new Comment();
export default comment;
