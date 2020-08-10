package com.wmp.joyboard.domain;

import org.junit.jupiter.api.Test;

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
}
