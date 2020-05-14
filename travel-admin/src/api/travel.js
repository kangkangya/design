import httpUtil from "@/utils/http";

const user_base_uri = 'user/';
const food_base_uri = 'food/';
const scenic_base_uri = 'scenic/';
const hotel_base_uri = 'hotel/';
const travelNotes_base_uri = 'travel-notes/'
const goWith_base_uri = 'go-with/'
const scenicDetail_base_uri = 'scenic-dtl/'
const comment_base_uri = 'comment/'


class TravelApi {
  /***********************user model api*************************/
  userList(data = {}) {
    return httpUtil.get(user_base_uri + "list", data);
  }
  userDel(data = {}) {
    return httpUtil.del(user_base_uri + "delete", data);
  }
  userEdit(data = {}) {
    return httpUtil.post(user_base_uri + "edit", data);
  }
  userSave(data = {}) {
    return httpUtil.post(user_base_uri + "save", data);
  }

/***********************food mode api*************************/
findFoodById(data = {}){
  return httpUtil.get(food_base_uri +'findById', data);
}
foodList(data = {}) {
  return httpUtil.get( food_base_uri + 'list', data);
}
foodDel(data ={}) {
  return httpUtil.del(food_base_uri + "delete", data);
}
foodEdit(data ={}) {
  return httpUtil.post(food_base_uri + "edit", data);
}
foodSave(data={}) {
  return httpUtil.post(food_base_uri + "save", data);

}

  /***********************scenic mode api*************************/
  scenicList(data = {}) {
    return httpUtil.get( scenic_base_uri + 'list', data);
  }
  scenicDel(data ={}) {
    return httpUtil.del(scenic_base_uri + "delete", data);
  }
  scenicEdit(data ={}) {
    return httpUtil.post(scenic_base_uri + "edit", data);
  }
  scenicSave(data={}) {
    return httpUtil.post(scenic_base_uri + "save", data);

  }

  /***********************scenicDetail mode api*************************/
  scenicDetailList(data = {}) {
    return httpUtil.get( scenicDetail_base_uri + 'findById', data);
  }
  scenicDetailDel(data ={}) {
    return httpUtil.del(scenicDetail_base_uri + "delete", data);
  }
  scenicDetailEdit(data ={}) {
    return httpUtil.post(scenicDetail_base_uri + "edit", data);
  }
  scenicDetailSave(data={}) {
    return httpUtil.post(scenicDetail_base_uri + "save", data);

  }

  /***********************hotle mode api*************************/

  findHotelById(data = {}){
    return httpUtil.get(hotel_base_uri + 'findById',data);
  }
  hotelList(data = {}) {
    return httpUtil.get( hotel_base_uri + 'list', data);
  }
  hotelDel(data ={}) {
    return httpUtil.del(hotel_base_uri + "delete", data);
  }
  hotelEdit(data ={}) {
    return httpUtil.post(hotel_base_uri + "edit", data);
  }
  hotelSave(data={}) {
    return httpUtil.post(hotel_base_uri + "save", data);
  }

  /***********************travelNotes mode api*************************/

  travelNotesList(data = {}) {
    return httpUtil.get( travelNotes_base_uri + 'list', data);
  }
  travelNotesDel(data ={}) {
    return httpUtil.del(travelNotes_base_uri + "delete", data);
  }
  travelNotesEdit(data ={}) {
    return httpUtil.post(travelNotes_base_uri + "edit", data);
  }
  travelNotesSave(data={}) {
    return httpUtil.post(travelNotes_base_uri + "save", data);

  }

  /***********************goWith mode api*************************/
  goWithList(data = {}) {
    return httpUtil.get( goWith_base_uri + 'list', data);
  }
  goWithDel(data ={}) {
    return httpUtil.del(goWith_base_uri + "delete", data);
  }
  goWithEdit(data ={}) {
    return httpUtil.post(goWith_base_uri + "edit", data);
  }
  goWithSave(data={}) {
    return httpUtil.post(goWith_base_uri + "save", data);

  }
  goWithFindById(data={}){
    return httpUtil.get(goWith_base_uri + "findById",data);
  }

  /************************comment mode api************************/
  commentList(data = {}){
    return httpUtil.get(comment_base_uri + "list", data);
  }


}

let travelApi = new TravelApi();
export default travelApi;
