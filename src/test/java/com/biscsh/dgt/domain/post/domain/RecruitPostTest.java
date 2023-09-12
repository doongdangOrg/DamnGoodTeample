package com.biscsh.dgt.domain.post.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static com.biscsh.dgt.domain.post.PostData.postRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RecruitPostTest {

    private Validator validator = null;

    @BeforeEach
    public void setupValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("공고 생성 시 validation 잘 동작하는지 테스트")
    public void validate_create_post() throws Exception {
        //given
        RecruitPost recruitPost = RecruitPost.of(postRequest(), 1L);
        //when
        Set<ConstraintViolation<RecruitPost>> violations = validator.validate(recruitPost);
        //then
//        violations.forEach((System.out::println));
        assertEquals(1, violations.size());
//        assertThrows(NullPointerException.class,() ->  new Post(1L, null, "fa", "article", 5, new ActivatePeriod(), 0, new RecruitPeriod(), false));
    }

    @Test
    @DisplayName("ActivePeriod의 Duration 계산 확인")
    public void check_duration_ofActivatePeriod() throws Exception {
        //given
        ActivatePeriod activatePeriod = ActivatePeriod.of(LocalDateTime.of(2023, 7, 11, 1, 40), LocalDateTime.of(2023, 7, 11, 1, 43));
        //when
        Duration between = Duration.between(LocalDateTime.of(2023, 7, 11, 1, 40), LocalDateTime.of(2023, 7, 11, 1, 43));

        //then
        assertThat(activatePeriod.getDuration()).isPositive();
        assertEquals(between, activatePeriod.getDuration());
    }
}