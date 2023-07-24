package com.biscsh.dgt.domain.post.dao;

import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.domain.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static com.biscsh.dgt.domain.post.PostData.postRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@ActiveProfiles("test")
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("공고 올리기")
    public void createPost() {
        //given
        Post post = Post.of(postRequest(), 1L);
        //when
        Post savedPost = postRepository.save(post);
        //then
        assertThat(savedPost)
                .returns("공fea", Post::getTitle)
                .returns("z", Post::getArticle)
                .returns(5, Post::getRecruitCnt)
                .returns(false, Post::isTest)
                .returns(ActivatePeriod.of(LocalDateTime.of(2023, 7, 19, 12, 43, 10), LocalDateTime.of(2023, 8, 1, 0, 0)), Post::getActivatePeriod)
                .returns(ActivatePeriod.of(LocalDateTime.of(2023, 7, 19, 12, 43, 10), LocalDateTime.of(2023, 8, 1, 0, 0)), Post::getRecruitPeriod);
    }
}