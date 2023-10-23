package com.biscsh.dgt.domain.post.service;

import com.biscsh.dgt.domain.post.dao.PostRepository;
import com.biscsh.dgt.domain.post.domain.RecruitPost;
import com.biscsh.dgt.domain.post.dto.PostIdResponse;
import com.biscsh.dgt.domain.post.dto.RecruitPostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static com.biscsh.dgt.domain.post.PostData.recruitPostRequest;
import static com.biscsh.dgt.domain.post.PostData.postResponse;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class RecruitPostServiceTest {
    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("공고 생성 서비스 계층 테스트")
    public void postCreate() throws Exception {
        // given
        RecruitPostRequest request = recruitPostRequest();
        PostIdResponse response = postResponse();
        lenient().when(postRepository.save(any(RecruitPost.class)))
                .thenReturn(request.toEntity(1L));
        // when
        PostIdResponse postIdResponse = postService.createPost(request.toEntity(1L));

        // then
        assertThat(postIdResponse.getPostId()).isEqualTo(response.getPostId());

    }

}