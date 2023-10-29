package com.biscsh.dgt.domain.post.domain.post;

import jakarta.validation.constraints.Size;

public class Article {
    @Size(max = 4000, message = "4000글자 내로 작성돼야 합니다.")
    private String value;
}
