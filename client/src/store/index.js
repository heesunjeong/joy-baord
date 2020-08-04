import Vue from 'vue'
import Vuex from 'vuex'
import * as types from './types';
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {
    [types.BOARD_LIST]: ({commit, dispatch}, {boardId}) => {
      return axios.get(`/api/boards/${boardId}/posts`,)
        .then(res => res.data);
    },

    [types.PAGING_BOARD_LIST]: ({commit, dispatch}, {boardId, offset}) => {
      return axios.get(`/api/boards/${boardId}/posts`, {params: {offset: offset, pageSize: 10}})
        .then(res => res.data);
    },

    [types.GET_POST]: ({commit, dispatch}, {postId}) => {

      return axios.get(`/api/posts/${postId}`,)
        .then(res => res.data);
    },

    [types.WRITE_POST]: ({dispatch}, {post}) => {
      if (post.file) {
        dispatch(types.FILE_UPLOAD, {file: post.file}).then(r => console.log(r));
      }

      return axios.post(`/api/posts`, post)
        .then(res => res.headers.location);
    },

    [types.EDIT_POST]: ({}, {id, post}) => {
      axios.put(`/api/posts/${id}`, post);
      return `posts/${id}`;
    },

    [types.DELETE_POST]: ({}, {postId}) => {
      return axios.delete(`/api/posts/${postId}`)
        .then(res => res.data);

    },

    [types.FILE_UPLOAD]: ({}, {file}) => {
      let formData = new FormData();
      formData.append("file", file);

      return axios.post(`/api/file/upload`, formData)
        .then(res => res.data);
    },

    [types.SEARCH_KEYWORD]: ({}, {keyword}) => {

      return axios.get(`/api/posts`, {params: {keyword: keyword}})
          .then(res => res.data);
    }

  }
})
