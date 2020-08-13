package com.wmp.joyboard.controller;

import com.wmp.joyboard.domain.Post;
import com.wmp.joyboard.dto.PostRequestDto;
import com.wmp.joyboard.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    private final PostController postController;
    private final PostService postService;

    public PostControllerTest() {
        this.postService = mock(PostService.class);
        this.postController = new PostController(postService);
    }

    @Test
    void createPost() {
        // given
        when(postService.createPost(any())).thenReturn(Post.builder().id(1L).build());
        PostRequestDto request = new PostRequestDto();
        ReflectionTestUtils.setField(request, "boardId", 1L);

        // when
        ResponseEntity post = postController.createPost(request);

        // then
        verify(postService).createPost(any());
        assertThat(post.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
