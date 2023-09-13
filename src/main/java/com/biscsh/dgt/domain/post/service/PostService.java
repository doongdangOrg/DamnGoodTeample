package com.biscsh.dgt.domain.post.service;

import com.biscsh.dgt.domain.post.dao.PostRepository;
import com.biscsh.dgt.domain.post.domain.Post;
import com.biscsh.dgt.domain.post.domain.PostContext;
import com.biscsh.dgt.domain.post.domain.RecruitPost;
import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final PostContext postContext;

    // TODO: Controller에서 Request받은 뒤 팩토리에 넘겨서 처리 후 서비스에 넘기자
    @Transactional
    public PostResponse createPost(Post post, Long memberId) {

        return PostResponse.of(postRepository.save(post));
    }
}
