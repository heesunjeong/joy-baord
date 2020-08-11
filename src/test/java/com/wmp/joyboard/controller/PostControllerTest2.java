package com.wmp.joyboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmp.joyboard.domain.Post;
import com.wmp.joyboard.dto.PostRequestDto;
import com.wmp.joyboard.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest2 {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    private final ObjectMapper objectMapper;


    public PostControllerTest2() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void createPost() throws Exception {
        // given
        PostRequestDto request = new PostRequestDto();
        ReflectionTestUtils.setField(request, "boardId", 1L);
        ReflectionTestUtils.setField(request, "title", "hello");
        ReflectionTestUtils.setField(request, "author", "joy");
        ReflectionTestUtils.setField(request, "contents", "world");
        when(postService.createPost(any())).thenReturn(Post.builder().id(1L).build());

        // when
        ResultActions result = mockMvc.perform(
                post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());

        //then
        result.andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void createPost_invalidRequest() throws Exception {
        // given
        PostRequestDto request = new PostRequestDto();
        ReflectionTestUtils.setField(request, "boardId", 1L);
        when(postService.createPost(any())).thenReturn(Post.builder().id(1L).build());

        // when
        MvcResult result = mockMvc.perform(
                post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn();

        // then
        assertThatThrownBy(() -> {
            throw result.getResolvedException();
        })
                .isInstanceOf(MethodArgumentNotValidException.class)
                .hasMessageContaining("제목은 필수값 입니다.");
    }
}
