package com.wmp.joyboard.controller;

import com.wmp.joyboard.dto.PostResponseDto;
import com.wmp.joyboard.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("boards")
@RestController
public class BoardController {

    private final PostService postService;

    public BoardController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{boardId}/posts")
    public ResponseEntity findPostsByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(PostResponseDto.listOf(postService.findAllByBoardId(boardId)));
    }

}
