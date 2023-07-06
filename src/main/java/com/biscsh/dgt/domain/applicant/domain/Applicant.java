package com.biscsh.dgt.domain.applicant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// FIXME: JPA에서 단일 PK없이 처리하려면 다른 방식 취해야함
//@Entity
@Table(name = "applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"memberId", "postId"})
@ToString(of = {"score"})
@IdClass(ApplicantId.class)
public class Applicant {

    @Id
    @Column(name = "member_id")
    @NotNull
    private Long memberId;

    @Id
    @Column(name = "post_id")
    @NotNull
    private Long postId;

    private int score;
}
