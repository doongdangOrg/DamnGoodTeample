package com.biscsh.dgt.domain.post.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @NotNull
    private Long memberId;


    @Size(min = 2, max = 100, message = "제목은 최소 2글자 ~ 100글자 내로 작성돼야 합니다.")
    private String title;

    @Size(max = 4000, message = "4000글자 내로 작성돼야 합니다.")
    private String article;


    // FIXME: 파일로 저장할 건지 S3에 넘겨서 URL로 저장할건지 결정 필요
    private String photo;


    private int recruitCnt;

    // TODO: 객체 구현해야 함 (시작일 - 종료일 , 대략적인 날짜(int))
    // 시작일 - 종료일 (선택시)-> 대략적인 날짜를 계산해서 저장함(3개월).
    @Embedded
    private ActivatePeriod activatePeriod;

    private int viewCnt;

    /*
        TODO: 객체 구현해야 함 (시작일 - 종료일 , 대략적인 날짜(int))
            if ActivatePeriod와 내용이 같다면 ActivatePeriod로 대체해도 된다.
     */
    @Embedded
    private RecruitPeriod recruitPeriod;
}
