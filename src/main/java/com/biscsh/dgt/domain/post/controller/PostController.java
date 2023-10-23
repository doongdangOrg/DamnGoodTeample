package com.biscsh.dgt.domain.post.controller;

import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostIdResponse;
import com.biscsh.dgt.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostIdResponse> createPost(@RequestBody PostRequest postRequest) {
        // TODO: 로그인 완성되면 세션에서 memberId꺼내 쓸 예정

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(postRequest.toEntity(1L)));
    }

    @PutMapping("/modify")
    public ResponseEntity<Void> modifyPost(@RequestBody PostRequest postRequest) {

        postService.createPost(postRequest.toEntity(1L));
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId) {

        postService.deletePost(postId, 1L);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
