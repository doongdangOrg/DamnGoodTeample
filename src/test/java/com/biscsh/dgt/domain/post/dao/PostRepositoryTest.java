package com.biscsh.dgt.domain.post.dao;

import com.biscsh.dgt.SpringJpaTest;
import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.domain.Post;
import com.biscsh.dgt.domain.post.domain.RecruitPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.biscsh.dgt.domain.post.PostData.postRequest;
import static com.biscsh.dgt.domain.post.PostData.recruitPostRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("공고 올리기")
    public void createRecruitPost() {
        //given
        RecruitPost recruitPost = recruitPostRequest().toEntity(1L);
        //when

        RecruitPost savedRecruitPost = postRepository.save(recruitPost);
        //then

        assertThat(savedRecruitPost)
                .returns("공fea", RecruitPost::getTitle)
                .returns("z", RecruitPost::getArticle)
                .returns(5, RecruitPost::getRecruitCnt)
                .returns(false, RecruitPost::isTest)
                .returns(ActivatePeriod.of(LocalDateTime.of(2023, 7, 19, 12, 43, 10), LocalDateTime.of(2023, 8, 1, 0, 0)), RecruitPost::getActivatePeriod)
                .returns(ActivatePeriod.of(LocalDateTime.of(2023, 7, 19, 12, 43, 10), LocalDateTime.of(2023, 8, 1, 0, 0)), RecruitPost::getRecruitPeriod);
    }

    @Test
    @DisplayName("게시글 올리기")
    public void createPost() throws Exception {
        //given
        Post post = postRequest().toEntity(1L);
        //when
        Post savedPost = postRepository.save(post);
        //then

        assertThat(savedPost)
                .returns("게시글1", Post::getTitle)
                .returns("게시글 원문", Post::getArticle)
                .returns(1L, Post::getId);

    }
}