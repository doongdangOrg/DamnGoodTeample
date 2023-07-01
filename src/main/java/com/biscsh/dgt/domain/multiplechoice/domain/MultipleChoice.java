package com.biscsh.dgt.domain.multiplechoice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "multiple_choice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "content"})
public class MultipleChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiple_choice_id")
    private Long id;

    @Size(max = 1000, message = "1000글자 내로 작성돼야 합니다.")
    private String content;

    @NotNull
    private Long questionId;

}
