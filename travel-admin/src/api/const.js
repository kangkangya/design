/*酒店相关常量*/
//主要设施
const mainFacility = [
  "wifi服务",
  "免费停车场",
  "电梯",
  "餐厅",
  "行李寄存",
  "24小时服务",
  "热水壶",
  "吹风机"
];
//酒店服务
const hotelService = [
  "洗衣服务",
  "取款服务",
  "旅游服务",
  "客房服务",
  "租房服务",
  "干洗服务",
  "快速入住服务",
  "叫车服务",
  "班车服务",
  "礼宾服务",
  "一次性账单结算",
  "信用卡结算",
  "行李服务",
  "24小时大堂经理"
];
//酒店设施
const hotelFacility = [
  "商务中心",
  "中餐厅",
  "棋牌室",
  "行政楼层",
  "会议设施",
  "吸烟区",
  "传真/复印",
  "茶室",
  "大堂吧",
  "无充电车位"
];
//房间设施
const roomFacility = [
  "叫醒服务",
  "国内长途电话",
  "24小时电话",
  "浴缸",
  "雨伞租借服务",
  "多种规格电源插座",
  "无烟房",
  "宽带上网",
  "空调",
  "阳台/露台",
  "电视",
  "暖气",
  "独立卫生间",
  "沙发",
  "露台",
  "拖鞋",
  "衣柜/衣橱",
  "110V电压插座",
  "免费洗漱用品",
  "遮光窗帘",
  "毯子或被子",
  "备用床具",
  "开床服务",
  "坐卧两用长沙发",
  "书桌",
  "手动窗帘",
  "闹钟",
  "免费报纸",
  "电话",
  "3D电视",
  "报纸"
];

class ConstApi{
  getMainFacility(){
    return mainFacility;
  }
  getHotelService(){
    return hotelService;
  }
  getHotelFacility(){
    return hotelFacility;
  }
  getRoomFacility(){
    return roomFacility;
  }
}

let conApi = new ConstApi();
export default conApi;


