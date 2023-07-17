package com.biscsh.dgt.domain.post.service;

import com.biscsh.dgt.domain.post.dao.PostRepository;
import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.domain.Post;
import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.biscsh.dgt.domain.post.PostData.postRequest;
import static com.biscsh.dgt.domain.post.PostData.postResponse;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("공고 생성 서비스 계층 테스트")
    public void postCreate() throws Exception {
        PostRequest request = postRequest();
        PostResponse response = postResponse();
        // service -> repository
        lenient().when(postRepository.save(any(Post.class)))
                .thenReturn(Post.of(request, 1L));
        // controller -> service
        PostResponse postResponse = postService.createPost(request, 1L);

        // service -> controller
        assertThat(postResponse.isPost()).isEqualTo(response.isPost());

    }

}