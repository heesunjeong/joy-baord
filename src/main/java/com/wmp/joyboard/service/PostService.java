package com.wmp.joyboard.service;

import com.wmp.joyboard.domain.Post;
import com.wmp.joyboard.dto.PostRequestDto;
import com.wmp.joyboard.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByBoardId(Long boardId) {
        return postRepository.findAllByBoardId(boardId);
    }

    @Transactional(readOnly = true)
    public long countPostByBoardId(Long boardId) {
        return postRepository.countPostByBoardId(boardId);
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id. id: " + id));
    }

    public Post createPost(PostRequestDto request) {
        return postRepository.save(request.toEntity());
    }

    public void updatePost(Long postId, PostRequestDto request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Request"));
        post.update(request.getBoardId(), request.getTitle(), request.getContents());
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
