package com.biscsh.dgt.domain.post.dto;

import com.biscsh.dgt.domain.post.domain.Post;
import com.biscsh.dgt.domain.post.domain.RecruitPost;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString(onlyExplicitlyIncluded = true)
@Builder
public class PostResponse {
    @ToString.Include
    private Long postId;

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .build();
    }
}
