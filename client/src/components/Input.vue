<template>
  <div class="input-area">
    <div>
      <label for="title">제목</label>
      <input type="text" id="title" v-model="title"></input>
    </div>

    <div>
      <label for="board">게시판</label>
      <select v-model="boardId" id="board">
        <option disabled value="">게시판을 선택해주세요</option>
        <option default>1</option>
        <option>2</option>
      </select>
    </div>

    <div>
      <label for="file-upload">File</label>
      <input id="file-upload" type="file" ref="file" v-on:change="handleFileUpload()"/>
    </div>

    <div>
      <label for="contents">내용</label>
      <textarea id="contents" v-model="contents"></textarea>
    </div>
    <button v-on:click="write">작성</button>
    <button v-on:click="cancel">취소</button>
  </div>
</template>

<script>
  import * as types from '../store/types';

  export default {
    name: "Input",
    data() {
      return {
        id: 0,
        title: '',
        boardId: '',
        contents: '',
        file: '',
      }
    },
    mounted() {
      this.id = this.$route.query.id;

      if(this.id) {
        this.$store.dispatch(types.GET_POST, {postId: id})
                .then(result => {
                  this.title = result.title;
                  this.boardId = result.boardId;
                  this.contents = result.contents;
                });
      }
    },
    methods: {
      write() {
        const post = {id: this.id, title: this.title, contents: this.contents, author: 1, boardId: this.boardId, file: this.file};

        this.request(this.id, post);
        this.$router.push({path: '/'})
      },

      cancel() {
        this.$router.back();
      },

      handleFileUpload() {
        this.file = this.$refs.file.files[0];
        // this.files = this.$refs.files.files;
      },

      request(id, post) {
        const type = !id ? types.WRITE_POST : types.EDIT_POST;
        this.$store.dispatch(type, {post});
      }
    }
  }
</script>

<style scoped>
  .input-area {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
    text-align: left;
  }

  input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
  }

  textarea {
    width: 100%;
    height: 150px;
    padding: 12px 20px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: #f8f8f8;
    resize: none;
  }

  button {
    background-color: #e7e7e7;
    color: #000;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 0 2px;
  }
</style>
