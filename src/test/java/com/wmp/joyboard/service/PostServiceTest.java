package com.wmp.joyboard.service;

import com.wmp.joyboard.domain.Post;
import com.wmp.joyboard.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    private final PostRepository postRepository;
    private final PostService postService;

    public PostServiceTest(@Mock PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postService = new PostService(postRepository);
    }

    @Test
    void findById() {
        given(postRepository.findById(any())).willReturn(Optional.ofNullable(Post.builder().id(1L).build()));

        postService.findById(4L);

        verify(postRepository).findById(1L);
    }

    @Test
    void findByIdThrowInvalidArgumentException() {
        given(postRepository.findById(1L)).willReturn(Optional.empty());

        assertThatIllegalArgumentException().isThrownBy(() -> postService.findById(1L))
                .withMessageStartingWith("Invalid post id.");

    }
}
