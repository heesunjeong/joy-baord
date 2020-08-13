package com.wmp.joyboard.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {
    @Test
    void update() {
        Post post = Post.builder().id(1L).boardId(1L).title("test1").author("joy.jeong").contents("test1").build();

        post.update(1L, "test2", "test2");

        assertThat(post.getId()).isEqualTo(1L);
        assertThat(post.getTitle()).isEqualTo("test2");
        assertThat(post.getAuthor()).isEqualTo("joy.jeong");
        assertThat(post.getContents()).isEqualTo("test2");
    }

    @Test
    void validate() {
        // given
        Post post = Post.builder().id(1L).boardId(1L).title("test1").author("정희선").contents("test1").build();

        // when
        boolean result = post.validateAuthor();

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"조이", "joy"})
    void validate_invalidName(String name) {
        // given
        Post post = Post.builder().id(1L).boardId(1L).title("test1").author(name).contents("test1").build();

        // when
        boolean result = post.validateAuthor();

        // then
        assertThat(result).isFalse();
    }
}
