package com.biscsh.dgt.domain.applicant.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

// FIXME: JPA에서 단일 PK없이 처리하려면 다른 방식 취해야함
//@Entity
@Table(name = "applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"memberId", "postId"})
@ToString(of = {"score"})
public class Applicant {

    @NotNull
    private Long memberId;

    @NotNull
    private Long postId;

    private int score;
}
