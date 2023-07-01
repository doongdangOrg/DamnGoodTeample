package com.biscsh.dgt.domain.multiplechoice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


// FIXME: 객관식 보기와 답을 분리할지 속성을 추가해서 정답처리를 할지 다시 고민
@Entity
@Table(name = "multiple_choice_answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "content"})
public class MultipleChoiceAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiple_choice_answer_id")
    private Long id;

    @Size(max = 1000, message = "1000글자 내로 작성돼야 합니다.")
    private String content;

    @NotNull
    private Long questionId;
}
