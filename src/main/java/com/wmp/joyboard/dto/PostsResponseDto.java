package com.wmp.joyboard.dto;

import com.wmp.joyboard.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    private long totalCount;
    private List<PostResponseDto> list;

    @Builder
    public PostsResponseDto(long totalCount, List<Post> list) {
        this.totalCount = totalCount;
        this.list = PostResponseDto.listOf(list);
    }
}
