<template>
  <div class="container">
    <div class="row" style="padding-top: 20px;" @click="closeButton(comment.id)" v-for="comment in commentList">
      <div class="col-1">
        <p class="card-text" style="margin-left: 20px;">
          <img class="img-circle image-responsive"
               style="width: 40px;height: 40px;left: 0px;"
               :src="imgUrl + 'user/' + comment.user.picUrl" alt="头像"/>
        </p>
      </div>
      <div class="col-11">
        <div class="row">
          <p class="card-text" style="color: orange;">
            {{comment.user.nickName}}
          </p>
          <el-rate style="margin-left: 20px;" v-model="comment.starNum" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
        </div>

        <div class="row">
          <div class="col-11">
            <p class="card-text text-muted comments" style="font-size: 14px;font-family: SimHei">
              {{comment.comment}}
            </p>
          </div>
          <div class="col-1"/>
        </div>

        <div class="row" style="margin-top: 10px">
          <div class="card-group" style="margin: 6px;">
            <div class="card" style="margin-left: 30px;" v-for="picUrl in comment.picUrls">
              <img :src="imgUrl + picUrl.picUrl" class="card-img-top" alt="...">
            </div>
          </div>
        </div>

        <div class="row" style="margin-bottom: 5px;margin-top: 10px;">
          <div class="col-2" style="margin-left: 20px"><small class="text-muted">
            {{comment.timestamp | dateFormat}}
          </small></div>
          <div class="col-7"/>
          <div class="col-2 btn" style="margin-left: 10px"><small style="color: orange" @click.stop="showButton(comment.id)">评论</small></div>
        </div>

        <div style="border-top: 1px dashed #ccc;width: 920px;"></div>

        <div class="row">
          <div class="container" @click="showReplayButton=false">
            <div class="row" style="padding-top: 20px;" v-for="replys in comment.replyList">
              <div class="col-1">
                <p class="card-text" style="margin-left: 20px;">
                  <img class="img-circle image-responsive"
                       style="width: 20px;height: 20px;"
                       :src="imgUrl + 'user/' + replys.user.picUrl" alt="头像"/>
                </p>
              </div>
              <div class="col-10" style="margin-left: -40px;font-size: 13px;font-family: SimHei">
                <small style="color: orange">{{replys.user.nickName}}</small>
                <small v-if="replys.type == 1" class="card-text text-muted comments" style="font-size: 10px;font-family: SimHei">
                  ：{{replys.comment}}
                </small>
                <small v-if="replys.type == 2" class="card-text text-muted comments" style="font-size: 10px;font-family: SimHei">
                  {{replys.comment}}
                </small>
                <small class="btn" style="color: orange;font-size: 10px" @click.stop="goReplay(replys.user.nickName)">回复</small>

                <div class="row">
                  <div class="col"><small class="text-muted"> {{replys.timestamp | dateFormat}}</small></div>
                </div>
              </div>
              <div class="col-1"/>

              <div style="border-top: 1px dashed #ccc;width: 920px;margin-top: 10px;margin-left: 18px;"></div>
            </div>

            <div class="row" v-show="showReplayButton" style="margin-left:10px;width: 88%;">
              <textarea @click.stop="showReplayButton=true" rows="5" cols="180"
                        style="font-size: 12px;margin-left: 5px;border-color: orange"
                        :placeholder="replayPlaceholder" v-model="replyMsg">
              </textarea>
              <small class="btn" style="color: orange;font-size: 13px;font-weight: bold;margin-left: 94%;"
                     @click.stop="showReplayButton=true" @click="replayComment(comment.id, 2)">回复</small>
            </div>
          </div>
        </div>

        <div class="row" :id="comment.id"  @click.stop="showButton(comment.id)" style="margin-left:10px;width: 88%;display: none">
          <textarea rows="5" v-model="replyMsg" cols="170"  style="width:100%;font-size: 12px;margin-left: 5px;border-color: orange" placeholder="发表评论:"></textarea>
          <small class="btn" style="color: orange;font-size: 13px;font-weight: bold;margin-left: 94%;" @click="replayComment(comment.id, 1)">回复</small>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col" style="left: 15%; margin-top: 60px;">
        <pager :pageParam="pageParam" :page-num-change="pageNumChange"/>
      </div>
    </div>
  </div>
</template>

<script>
  import config from '../../config/index'
  import pager from "../components/pager"
  import comment1 from "../api/comment";
  import replayApi from "../api/replay";
  import timeUtil from "../utils/time";

  export default {
    components: {
      pager
    },
    filters:{
      dateFormat: function (val) {
        let date = new Date(val);
        return timeUtil.format(date, 'yyyy-MM-dd hh:mm:ss')
      }
    },
    data() {
      return {
        date: '',
        avgScore: 0.0,
        imgUrl: config.dev.imgUrl,
        commentList: [],
        replyMsg: '',
        showReplayButton: false,
        replayPlaceholder: '',
        query: {
          refId: this.refId,
          type: this.type,
          pageNum: 1,
          pageSize: 10
        },
        pageParam: {
          total: 0,
          pageSize: 10,
          pageNum: 1,
          totalPage: 0
        }
      }
    },
    props: {
      refId: String,
      type: String
    },
    methods: {
      goReplay(nickName) {
        this.showReplayButton = !this.showReplayButton;
        this.replayPlaceholder = '回复' + nickName + " ：";
      },

      messageError(m) {
        this.$message.error({
          message: m
        })
      },

      messageSuccess() {
        this.$message({
          message: '评论成功',
        })
      },

      pageNumChange(newPageNum, oldPageNum) {
        this.pageParam.pageNum = newPageNum;
        this.getCommentList(newPageNum);
      },

      closeButton(id) {
        let div1 = document.getElementById(id);
        div1.style.display = 'none';
      },

      showButton(id) {
        let div1 = document.getElementById(id);
        div1.style.display = 'block';
      },

      getCommentList(pageNum) {
        this.query.pageNum = pageNum;
        this.query.pageSize = this.pageParam.pageSize;

        comment1.list(this.query).then(res => {
          this.commentList = res.data.body
          this.pageParam.total = res.data.total
          this.pageParam.totalPage = res.data.totalPage

          let length = this.commentList.length;
          let sum = 0.0;
          if (length > 0 ){
            for (let c of this.commentList){
              sum += c.starNum;
            }
            this.avgScore = sum/length;
          }

        })
      },

      replayComment(id, type) {

        if(this.replyMsg === '' ||
          this.replyMsg === undefined ||
          this.replyMsg === null) {
          this.messageError('评论内容不能为空！');
          return;
        }

        let c = this.replyMsg;

        if(type === 2) {
          c = this.replayPlaceholder + this.replyMsg;
        }

        let userInfo = JSON.parse(localStorage.getItem('userInfo'));

        let params = {
          comment: c,
          userId: userInfo.id,
          refId: id,
          type: type
        }

        replayApi.save(params).then(res => {
          this.replyMsg = ''

          this.closeButton(id);

          this.showReplayButton = false;

          this.getCommentList(this.pageParam.pageNum);
        })
      }
    },
    mounted() {
      this.getCommentList(this.pageParam.pageNum);
    }
  }
</script>

<style scoped>
</style>
