package com.wmp.joyboard.dto;

import com.wmp.joyboard.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private Long id;
    private Long boardId;
    private String title;
    private String author;
    private String contents;

    public Post toEntity() {
        return Post.builder()
                .boardId(this.boardId)
                .title(this.title)
                .author(this.author)
                .contents(this.contents)
                .build();
    }
}
