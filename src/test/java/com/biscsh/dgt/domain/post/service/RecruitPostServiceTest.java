package com.biscsh.dgt.domain.post.service;

import com.biscsh.dgt.domain.post.dao.PostRepository;
import com.biscsh.dgt.domain.post.domain.RecruitPost;
import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static com.biscsh.dgt.domain.post.PostData.postRequest;
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
        PostRequest request = postRequest();
        PostResponse response = postResponse();
        lenient().when(postRepository.save(any(RecruitPost.class)))
                .thenReturn(RecruitPost.builder()
                        .id(1L)
                        .title(request.getTitle())
                        .article(request.getArticle())
                        .activatePeriod(request.getActivatePeriod())
                        .recruitPeriod(request.getRecruitPeriod())
                        .isTest(request.isTest())
                        .build());
        // when
        PostResponse postResponse = postService.createPost(request, 1L);

        // then
        assertThat(postResponse.getPostId()).isEqualTo(response.getPostId());

    }

}