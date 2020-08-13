package com.wmp.joyboard.controller;

import com.wmp.joyboard.dto.PostsResponseDto;
import com.wmp.joyboard.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("boards")
@RestController
public class BoardController {

    private final PostService postService;

    public BoardController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{boardId}/posts")
    public ResponseEntity findPostsByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(PostsResponseDto.builder()
                .totalCount(postService.countPostByBoardId(boardId))
                .list(postService.findAllByBoardId(boardId))
                .build());
    }
}
