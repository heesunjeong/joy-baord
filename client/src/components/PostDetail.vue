<template>
  <div>
    <div class="post-header">
      <div class="post-title">{{post.title}}</div>
      <span>id {{id}}</span> ㅣ
      <span>작성자 {{post.author}}</span> ㅣ
      <span>작성일 {{post.modifiedAt?post.modifiedAt:post.createdAt}}</span>
    </div>

    <div class="post-contents">
      <div>{{post.contents}}</div>
    </div>

    <div class="post-comments">
      <div>Comments</div>
      <ul>
        <li id="comment_1540945916" class="comment-item">
          <div class="comment-author">
            <span>정조이</span>
            <span class="date">2020-07-28 17:12:59</span>
          </div>
          <span class="commentWarningMessage">블라블라 </span>
          <div class="fdb_nav img_tx">
          </div>
        </li>
      </ul>
    </div>

    <div class="post-footer">
      <button class="button" v-on:click="editPost">수정</button>
      <button class="button" v-on:click="deletePost">삭제</button>
      <button class="button" v-on:click="list">글목록</button>
    </div>

  </div>
</template>

<script>
  import * as types from '../store/types'

  export default {
    name: "BoardDetail",
    props: ["id"],
    data() {
      return {
        post: '',
      }
    },
    created() {
      this.$store.dispatch(types.GET_POST, {postId: this.id})
        .then(result => {
          this.post = result;
        })
    },
    methods: {
      editPost() {
        this.$router.push({path: '/input', query: {id: this.id}});
      },

      deletePost() {
        this.$store.dispatch(types.DELETE_POST, {postId: this.id})
          .then(result => result ? this.list() : alert("실패하였습니다."));
      },

      list() {
        this.$router.push({path: `/`});

      }
    }
  }
</script>

<style scoped>

  .post-header {
    background-color: #fff;
    padding: 20px 30px 20px 80px;
    border-bottom: 1px solid #eee;
    position: relative;
    line-height: 20px;
  }

  .post-title {
    font-weight: bold;
    font-size: 24px;
  }

  .post-contents {
    background-color: #fff;
    padding: 20px 30px 20px 80px;
    border-bottom: 1px solid #eee;
    position: relative;
    line-height: 20px;
    text-align: left;
  }

  .post-comments {
    background-color: #fff;
    padding: 20px 30px 20px 80px;
    border-bottom: 1px solid #eee;
    position: relative;
    line-height: 20px;
      text-align: left;
  }

  .post-footer {
    background-color: #fff;
    padding: 20px 30px 20px 80px;
    /*border-bottom: 1px solid #eee;*/
    position: relative;
    line-height: 20px;
  }

  .button {
    background-color: #e7e7e7;
    color: #000;
    border: none;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 2px;
  }

  .comment-item {
    position: relative;
    padding: 10px !important;
  }

  .comment-author {
    margin-bottom: 10px;
  }

  .comment-author .date {
    padding: 0 12px;
    font-size: 11px;
    color: #888;
  }

</style>
