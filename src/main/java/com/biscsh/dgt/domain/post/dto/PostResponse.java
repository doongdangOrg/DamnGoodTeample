package com.biscsh.dgt.domain.post.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString(onlyExplicitlyIncluded = true)
@Builder
public class PostResponse {
    @ToString.Include
    private boolean isPost;
}
