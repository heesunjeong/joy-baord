package com.wmp.joyboard.repository;

import com.wmp.joyboard.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        Post post = new Post(null, 1L, "This is JPA test", "Joy Joeng", "Hello World");
        Post post2 = new Post(null, 2L, "This is not JPA test", "Joy Joeng", "Hello World");
        postRepository.save(post);
        postRepository.save(post2);
    }

    @Test
    void save() {
        Post post = new Post(null, 1L, "This is JPA test", "Joy Joeng", "Hello World");

        Post savedPost = postRepository.save(post);

        assertThat(savedPost.getId()).isNotNull();
    }

    @Test
    void findAllByBoardId() {
        List<Post> posts = postRepository.findAllByBoardId(1L);

        assertThat(posts).hasSize(1);
        assertThat(posts.get(0)).extracting(Post::getBoardId).isEqualTo(1L);
        assertThat(posts.get(0)).extracting(Post::getTitle).isEqualTo("This is JPA test");
    }
}
