package com.wmp.joyboard.controller;

import com.wmp.joyboard.domain.Post;
import com.wmp.joyboard.dto.PostRequestDto;
import com.wmp.joyboard.dto.PostResponseDto;
import com.wmp.joyboard.dto.PostsResponseDto;
import com.wmp.joyboard.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity findPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findById(postId));
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequestDto request) {
        Post savedPost = postService.createPost(request);
        return ResponseEntity.created(URI.create("/posts/" + savedPost.getId())).build();
    }

    @PutMapping("{postId}")
    public ResponseEntity updatePost(@PathVariable("postId") Long postId, @RequestBody PostRequestDto request) {
        postService.updatePost(postId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
