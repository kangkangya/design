<template>

</template>

<script>
  import config from '../../config/index'
  import replay from "../api/replay";
  import timeUtil from "../utils/time";

  export default {
    data() {
      return {
        imgUrl: config.dev.imgUrl,
        showReplayButton: false,
        replayPlaceholder: '',
        replyCommentMsg: '',
        replyCommentList: [],
        refId1: this.refId
      }
    },
    filters:{
      dateFormat: function (val) {
        let date = new Date(val);
        return timeUtil.format(date, 'yyyy-MM-dd hh:mm:ss')
      }
    },
    props: {
      refId: String
    },
    methods: {
      goReplay(nickName) {
        this.showReplayButton = !this.showReplayButton;
        this.replayPlaceholder = '回复' + nickName + ":";
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

      replayComment() {
        if (this.replyCommentMsg === '' ||
          this.replyCommentMsg === undefined ||
          this.replyCommentMsg === null) {
          this.messageError('评论内容不能为空！');
          return;
        }

        let c = this.replayPlaceholder + this.replyCommentMsg;
        let userInfo = JSON.parse(localStorage.getItem('userInfo'));

        let params = {
          comment: c,
          userId: userInfo.id,
          refId: this.refId
        }

        replay.save(params).then(res => {
          this.showReplayButton = false;
          this.replyCommentMsg = ''
        })
      },

      getReplyList() {
        console.log(this.refId1)

        replay.list(this.refId1).then(res => {
          this.replyCommentList = res.data

          console.log(this.replyCommentList)
        })
      }
    },
    mounted() {
      this.getReplyList();
    }
  }
</script>

<style scoped>
</style>
