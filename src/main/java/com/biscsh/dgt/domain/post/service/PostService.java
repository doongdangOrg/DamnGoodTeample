package com.biscsh.dgt.domain.post.service;

import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostService {

    @Transactional
    public PostResponse createPost(PostRequest postRequest, Long memberId) {
        return PostResponse.builder().isPost(true).build();
    }
}
