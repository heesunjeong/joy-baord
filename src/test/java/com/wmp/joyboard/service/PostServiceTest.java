package com.wmp.joyboard.service;

import com.wmp.joyboard.domain.Post;
import com.wmp.joyboard.dto.PostRequestDto;
import com.wmp.joyboard.exception.InvalidAuthorNameException;
import com.wmp.joyboard.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
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
        given(postRepository.findById(any())).willReturn(Optional.ofNullable(Post.builder().id(4L).build()));

        postService.findById(4L);

        verify(postRepository).findById(4L);
    }

    @Test
    @DisplayName("post가 조회되지 않으면 exception이 발생한다.")
    void findByIdThrowInvalidArgumentException() {
        given(postRepository.findById(1L)).willReturn(Optional.empty());

        assertThatIllegalArgumentException().isThrownBy(() -> postService.findById(1L))
                .withMessageStartingWith("Invalid post id.");
    }

    @Test
    void createPost() {
        PostRequestDto request = new PostRequestDto();
        ReflectionTestUtils.setField(request, "boardId", 1L);
        ReflectionTestUtils.setField(request, "author", "정조이");

        postService.createPost(request);

        verify(postRepository).save(any());
    }

    @DisplayName("작성자 이름이 '조이'일때 exception 발생")
    @Test
    void createPost_invalidName() {
        PostRequestDto request = new PostRequestDto();
        ReflectionTestUtils.setField(request, "boardId", 1L);
        ReflectionTestUtils.setField(request, "author", "조이");

        assertThatThrownBy(() -> postService.createPost(request)).isInstanceOf(InvalidAuthorNameException.class);
    }
}
