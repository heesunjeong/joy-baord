package com.wmp.joyboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {
    public static final String NAME_JOY_KOREAN = "조이";
    public static final String NAME_JOY_ENGLISH = "joy";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
    private String title;
    private String author;
    private String contents;

    @Builder
    public Post(Long id, Long boardId, String title, String author, String contents) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    public void update(Long boardId, String title, String contents) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
    }

    public boolean validateAuthor() {
        return !author.equals(NAME_JOY_KOREAN) && !author.equals(NAME_JOY_ENGLISH);
    }
}
