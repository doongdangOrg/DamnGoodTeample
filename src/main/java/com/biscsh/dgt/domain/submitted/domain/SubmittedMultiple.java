package com.biscsh.dgt.domain.submitted.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

// FIXME: JPA에서 단일 PK없이 처리하려면 다른 방식 취해야함
//@Entity
@Table(name = "submitted_multiple")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"memberId", "multipleChoiceId","questionId"})
//@ToString(of = {"score"})
public class SubmittedMultiple {
    @NotNull
    private Long memberId;

    @NotNull
    private Long multipleChoiceId;

    @NotNull
    private Long questionId;
}
