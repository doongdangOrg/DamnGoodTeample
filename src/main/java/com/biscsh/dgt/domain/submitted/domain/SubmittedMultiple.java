package com.biscsh.dgt.domain.submitted.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// FIXME: JPA에서 단일 PK없이 처리하려면 다른 방식 취해야함
//@Entity
@Table(name = "submitted_multiple")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"memberId", "multipleChoiceId","questionId"})
//@ToString(of = {"score"})
@IdClass(SubmittedMultipleId.class)
public class SubmittedMultiple {
    @Id
    @Column(name = "member_id")
    @NotNull
    private Long memberId;

    @Id
    @Column(name = "multiple_choice_id")
    @NotNull
    private Long multipleChoiceId;

    @Id
    @Column(name = "question_id")
    @NotNull
    private Long questionId;
}
