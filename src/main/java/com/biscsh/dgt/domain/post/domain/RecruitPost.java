package com.biscsh.dgt.domain.post.domain;


import com.biscsh.dgt.domain.post.dto.PostRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("R")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class RecruitPost extends Post {

    @PositiveOrZero
    private int recruitCnt;

    // TODO: 객체 구현해야 함 (시작일 - 종료일 , 대략적인 날짜(int))
    // 시작일 - 종료일 (선택시)-> 대략적인 날짜를 계산해서 저장함(3개월).
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "activity_start_date")),
            @AttributeOverride(name = "end", column = @Column(name = "activity_end_date")),
            @AttributeOverride(name = "duration", column = @Column(name = "activity_duration"))
    })
    private ActivatePeriod activatePeriod;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "recruit_start_date")),
            @AttributeOverride(name = "end", column = @Column(name = "recruit_end_date")),
            @AttributeOverride(name = "duration", column = @Column(name = "recruit_duration"))
    })
    private ActivatePeriod recruitPeriod;

    @Column(name = "test_or_not")
    private boolean isTest;

}
