package com.biscsh.dgt.domain.post.controller;

import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import com.biscsh.dgt.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        // TODO: 로그인 완성되면 세션에서 memberId꺼내 쓸 예정

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(postRequest.toEntity(1L)));
    }

}
