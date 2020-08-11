package com.wmp.joyboard.dto;

import com.wmp.joyboard.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class PostRequestDto {
    private Long id;
    @NotNull
    private Long boardId;
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;
    @NotBlank
    private String author;
    @NotBlank
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
