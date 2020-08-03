<template>
  <div id="board">
    <table>
      <thead>
      <th>No</th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일자</th>
      </thead>

      <tbody>
      <tr v-for="(item, idx) of list" v-on:click="pushDetailPage(item.id)">
        <td>{{item.id}}</td>
        <td>{{item.title}}</td>
        <td>{{item.author}}</td>
        <td>{{formattingDate(item.createdAt)}}</td>
      </tr>
      </tbody>
    </table>

    <div class="page-nav">
      <span class="prev">< prev </span>
      <span>1 2 3 4</span>
      <span class="more"> more ></span>
    </div>

    <div class="board-footer">
      <input type="text" class="search-keyword" v-model="keyword"/>
      <input type="button" class="button" value="검색" v-on:click="searchKeyword"/>
      <button class="write-button" v-on:click="pushInputPage()">글 작성하기</button>
    </div>

  </div>
</template>

<script>
  import * as types from '../store/types'
  import moment from 'moment';
  import axios from 'axios'

  export default {
    name: "Board",
    props: ["boardId"],
    data() {
      return {
        list: '',
        totalCount: 0,
        keyword: ''
      }
    },
    created() {
      this.$store.dispatch(types.PAGING_BOARD_LIST, {boardId: this.boardId, offset: 1})
        .then(result => {
          console.log(result);
          this.list = result.list;
          this.totalCount = result.totalCount;
        });
    },
    methods: {
      pushDetailPage(postId) {
        this.$router.push({path: `/post/${postId}`});
      },

      pushInputPage() {
        this.$router.push({path: `/input`});
      },

      formattingDate(createdAt) {
        return moment(createdAt).format("YYYY-MM-DD H:mm");
      },

      searchKeyword() {
        this.$store.dispatch(types.SEARCH_KEYWORD, {keyword: this.keyword})
          .then(result => {
            this.list = result.list;
            this.totalCount = result.totalCount;
          });
      }
    },
  }
</script>

<style scoped>
  #board {
    position: absolute;
    margin: 30px 0;
    width: 100%;
    transition: all .5s cubic-bezier(.55, 0, .1, 1);
  }

  table {
    width: 100%;
    margin: 30px 0;
    list-style-type: none;
    padding: 0 20px;
    margin: 0;
  }

  th {
    background-color: #42b983;
    color: #040452;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
  }

  td {
    background-color: #f9f9f9;
  }

  th, td {
    background-color: #fff;
    padding: 20px 30px 20px 80px;
    border-bottom: 1px solid #eee;
    position: relative;
    line-height: 20px;
  }

  th.active {
    color: #fff;
  }

  th.active .arrow {
    opacity: 1;
  }

  .arrow {
    display: inline-block;
    vertical-align: middle;
    width: 0;
    height: 0;
    margin-left: 5px;
    opacity: 0.66;
  }

  .arrow.asc {
    border-left: 4px solid transparent;
    border-right: 4px solid transparent;
    border-bottom: 4px solid #fff;
  }

  .arrow.dsc {
    border-left: 4px solid transparent;
    border-right: 4px solid transparent;
    border-top: 4px solid #fff;
  }

  .page-nav {
    padding: 15px 30px;
    text-align: center;
    top: 55px;
    left: 0;
    right: 0;
    z-index: 998;
  }

  .page-nav .disabled {
    color: #ccc;
  }

  .page-nav .prev.more {
    margin: 0 1em;
  }

  .write-button, .button {
    background-color: #e7e7e7;
    color: #000;
    border: none;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 5px;
  }
  .search-keyword {
    padding: 0px 10px;
    height: 30px;
    line-height: 30px;
    font-size: 12px;
    width: 200px;
    border: 1px solid #ced4da;
    position: relative;
    top: 0px;
  }
</style>
