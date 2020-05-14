import httpUtil from '../utils/http'

const base_uri = 'comment-picture/'

class CommentPicture {
  delete(data = {}) {
    return httpUtil.del(base_uri + "delete", data);
  }

  save(data = {}) {
    return httpUtil.post(base_uri + "save", data);
  }
}

let commentPicture = new CommentPicture();
export default commentPicture;
