package com.biscsh.dgt.domain.post.domain.post;

import jakarta.validation.constraints.Size;
import lombok.ToString;

public class Title {

    @Size(min = 2, max = 100, message = "제목은 최소 2글자 ~ 100글자 내로 작성돼야 합니다.")
    @ToString.Include
    private String value;
}
