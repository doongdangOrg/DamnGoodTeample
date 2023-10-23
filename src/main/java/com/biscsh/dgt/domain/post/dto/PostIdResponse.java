package com.biscsh.dgt.domain.post.dto;

import com.biscsh.dgt.domain.post.domain.Post;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString(onlyExplicitlyIncluded = true)
@Builder
public class PostIdResponse {
    @ToString.Include
    private Long postId;

    public static PostIdResponse of(Post post) {
        return PostIdResponse.builder()
                .postId(post.getId())
                .build();
    }
}
