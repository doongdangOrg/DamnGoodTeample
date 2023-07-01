package com.biscsh.dgt.domain.question.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "content", "score", "type"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @NotNull
    private Long postId;

    @Size(max = 4000, message = "4000글자 내로 작성돼야 합니다.")
    private String content;

    // TODO: 0 ~ 몇점? 가중치는?
    private int score;

    @Enumerated(EnumType.STRING)
    private QuestionType type;
}
