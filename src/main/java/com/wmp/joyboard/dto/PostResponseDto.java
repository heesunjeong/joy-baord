package com.wmp.joyboard.dto;

import com.wmp.joyboard.domain.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private Long boardId;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public PostResponseDto(Long id, Long boardId, String title, String author, String contents,
                           LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.author = author;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .boardId(post.getBoardId())
                .title(post.getTitle())
                .author(post.getAuthor())
                .contents(post.getContents())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

    public static List<PostResponseDto> listOf(List<Post> posts) {
        return posts.stream()
                .map(PostResponseDto::of)
                .collect(Collectors.toList());
    }
}
