package com.biscsh.dgt.domain.post.dao;

import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.domain.RecruitPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static com.biscsh.dgt.domain.post.PostData.postRequest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class RecruitPostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("공고 올리기")
    public void createPost() {
        //given
        RecruitPost recruitPost = postRequest().toEntity(1L);
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
}