package com.biscsh.dgt.domain.post.service;

import com.biscsh.dgt.domain.post.dao.PostRepository;
import com.biscsh.dgt.domain.post.domain.Post;
import com.biscsh.dgt.domain.post.dto.PostIdResponse;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    // TODO: Controller에서 Request받은 뒤 팩토리에 넘겨서 처리 후 서비스에 넘기자
    @Transactional
    public PostIdResponse createPost(Post post) {
        return PostIdResponse.of(postRepository.save(post));
    }

    @Transactional
    public void modifyPost(Post post) {
        postRepository.save(post);
    }


    public void deletePost(Long postId, Long memberId) {
        postRepository.deleteById(postId);
    }

    public PostResponse readPost(Long postId) {
        return null;
    }
}
