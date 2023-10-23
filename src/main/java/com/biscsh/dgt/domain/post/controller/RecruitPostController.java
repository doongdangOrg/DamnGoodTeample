package com.biscsh.dgt.domain.post.controller;


import com.biscsh.dgt.domain.post.dto.PostIdResponse;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import com.biscsh.dgt.domain.post.dto.RecruitPostRequest;
import com.biscsh.dgt.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// FIXME: 세션에서 멤버 아이디 가져올 예정
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruits")
public class RecruitPostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostIdResponse> createPost(@RequestBody RecruitPostRequest postRequest) {
        // TODO: 로그인 완성되면 세션에서 memberId꺼내 쓸 예정

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(postRequest.toEntity(1L)));
    }

    @PutMapping("/modify")
    public ResponseEntity<Void> modifyPost(@RequestBody RecruitPostRequest postRequest) {

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

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> readPost(@PathVariable("id") Long postId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.readPost(postId));
    }
}
