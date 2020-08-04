package com.wmp.joyboard.repository;

import com.wmp.joyboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByBoardId(Long boardId);

    long countPostByBoardId(Long boardId);
}
