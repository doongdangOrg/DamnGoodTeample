package com.biscsh.dgt.domain.post.dto;

import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.domain.RecruitPeriod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class PostRequest {

    @Size(min = 2, max = 100, message = "제목은 최소 2글자 ~ 100글자 내로 작성돼야 합니다.")
    @ToString.Include
    @NotBlank
    private String title;

    @Size(max = 4000, message = "4000글자 내로 작성돼야 합니다.")
    @ToString.Include
    private String article;

    @ToString.Include
    @PositiveOrZero
    private int recruitCnt;

    // TODO: 객체 구현해야 함 (시작일 - 종료일 , 대략적인 날짜(int))
    // 시작일 - 종료일 (선택시)-> 대략적인 날짜를 계산해서 저장함(3개월).
    @ToString.Include
    private ActivatePeriod activatePeriod;

    @ToString.Include
    private ActivatePeriod recruitPeriod;

    @ToString.Include
    private boolean isTest;

}
