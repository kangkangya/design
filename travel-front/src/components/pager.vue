<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li class="page-item">
        <a class="page-link">
          <span>共{{this.pageParam.totalPage}}页/{{this.pageParam.total}}条 &#12288;</span>
        </a>
      </li>

      <li class="page-item">
        <a @click="startPage" class="page-link">首页</a>
      </li>
      <li v-for="i in displayPage" :key="'pageParam_'+i" :class="pageItemActive(i)">
        <a @click="selectPage(i)" class="page-link">{{i}}</a>
      </li>
      <li class="page-item">
        <a @click="endPage" class="page-link">尾页</a>
      </li>
    </ul>
  </nav>
</template>

<script>
  export default {
    name: "pager",
    data() {
      return {}
    },
    props: {
      pageParam: Object,
      pageNumChange: Function,
    },
    computed: {
      displayPage() {
        let totalPage = this.pageParam.total % this.pageParam.pageSize === 0 ? (parseInt(this.pageParam.total / this.pageParam.pageSize)) : (parseInt(this.pageParam.total / this.pageParam.pageSize) + 1)
        let pageNum = this.pageParam.pageNum;
        if (totalPage < 10) {
          return totalPage;
        } else {
          let arr = []
          if (pageNum < 5) {
            for (let i = 1; i <= 10; i++) {
              arr.push(i)
            }
          } else if (pageNum >= totalPage - 5) {
            for (let i = totalPage - 9; i <= totalPage; i++) {
              arr.push(i)
            }
          } else {
            for (let i = pageNum - 4; i <= pageNum + 5 && i < totalPage; i++) {
              arr.push(i)
            }
          }
          return arr
        }
      }
    },
    watch: {
      'pageParam.pageNum'(newValue, oldValue) {
        this.pageNumChange(newValue, oldValue)
      },
    },
    methods: {
      pageItemActive(i) {
        if (i == this.pageParam.pageNum) {
          return 'page-item active'
        } else {
          return 'page-item'
        }
      },
      selectPage(pageNum) {
        this.pageParam.pageNum = pageNum
      },
      startPage() {
        if (this.pageParam.pageNum !== 1) {
          this.pageParam.pageNum = 1
        }
      },
      endPage() {
        let totalPage = this.pageParam.total % this.pageParam.pageSize === 0 ? (parseInt(this.pageParam.total / this.pageParam.pageSize)) : (parseInt(this.pageParam.total / this.pageParam.pageSize) + 1)
        if (this.pageParam.pageNum !== totalPage) {
          this.pageParam.pageNum = totalPage
        }
      }
    }
  }
</script>
