package com.wmp.joyboard.controller;

import com.wmp.joyboard.dto.PostRequestDto;
import com.wmp.joyboard.dto.PostResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class PostControllerTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void acceptanceTest() {
        // create post
        ExtractableResponse<Response> createResponse = RestAssured
                .given().log().all().
                        contentType(ContentType.JSON).
                        body(this.getPostRequest(1L, "hello", "joy.jeong", "world!"))
                .when().
                        post("/posts")
                .then().log().all().
                        statusCode(201).
                        assertThat().
                        header("Location", notNullValue())
                .extract();

        // read post
        PostResponseDto response = RestAssured
                .given().log().all()
                .when().
                        get(createResponse.header("Location"))
                .then().log().all().
                        statusCode(HttpStatus.OK.value()).
                        contentType(ContentType.JSON).
                        extract().as(PostResponseDto.class);

        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo("hello");
        assertThat(response.getAuthor()).isEqualTo("joy.jeong");


        // update post
        ExtractableResponse<Response> updateResponse = RestAssured
                .given().log().all().
                        contentType(ContentType.JSON).
                        body(this.getPostRequest(1L, "hello2", "dave.hth", "world!"))
                .when().
                        put(createResponse.header("Location"))
                .then().log().all().
                        statusCode(HttpStatus.OK.value()).
                        extract();

        PostResponseDto response2 = RestAssured
                .given().log().all()
                .when().
                        get(createResponse.header("Location"))
                .then().log().all().
                        statusCode(HttpStatus.OK.value()).
                        contentType(ContentType.JSON).
                        extract().as(PostResponseDto.class);

        assertThat(response2).isNotNull();
        assertThat(response2.getTitle()).isEqualTo("hello2");
        assertThat(response2.getAuthor()).isEqualTo("joy.jeong");

        // delete post


    }

    private PostRequestDto getPostRequest(Long boardId, String title, String author, String contents) {
        PostRequestDto request = new PostRequestDto();
        ReflectionTestUtils.setField(request, "boardId", boardId);
        ReflectionTestUtils.setField(request, "title", title);
        ReflectionTestUtils.setField(request, "author", author);
        ReflectionTestUtils.setField(request, "contents", contents);

        return request;
    }

}
