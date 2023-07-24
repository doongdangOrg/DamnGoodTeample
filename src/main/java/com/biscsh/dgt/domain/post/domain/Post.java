package com.biscsh.dgt.domain.post.domain;


import com.biscsh.dgt.domain.post.dto.PostRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotNull
    private Long memberId;

    @Size(min = 2, max = 100, message = "제목은 최소 2글자 ~ 100글자 내로 작성돼야 합니다.")
    @ToString.Include
    private String title;

    @Size(max = 4000, message = "4000글자 내로 작성돼야 합니다.")
    private String article;

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

    private int viewCnt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "recruit_start_date")),
            @AttributeOverride(name = "end", column = @Column(name = "recruit_end_date")),
            @AttributeOverride(name = "duration", column = @Column(name = "recruit_duration"))
    })
    private ActivatePeriod recruitPeriod;

    @Column(name = "test_or_not")
    private boolean isTest;

    public static Post of(@NotNull PostRequest postRequest, @NotNull Long memberId) {
        return Post.builder()
                .memberId(memberId)
                .title(postRequest.getTitle())
                .article(postRequest.getArticle())
                .recruitCnt(postRequest.getRecruitCnt())
                .activatePeriod(postRequest.getActivatePeriod())
                .recruitPeriod(postRequest.getRecruitPeriod())
                .isTest(postRequest.isTest())
                .build();
    }
}
