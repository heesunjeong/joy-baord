package com.wmp.joyboard.service;

import com.wmp.joyboard.domain.Post;
import com.wmp.joyboard.dto.PostRequestDto;
import com.wmp.joyboard.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllByBoardId(Long boardId) {
        return postRepository.findAllByBoardId(boardId);
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id. id: " + id));
    }

    public Post createPost(PostRequestDto request) {
        return postRepository.save(request.toEntity());
    }

    public Post updatePost(Long postid, PostRequestDto request) {
        Post post = postRepository.findById(postid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Request"));
        post.update(request.getBoardId(), request.getTitle(), request.getContents());

        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
