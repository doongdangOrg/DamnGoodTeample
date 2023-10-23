package com.biscsh.dgt.domain.post.dto;

import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.domain.Post;
import com.biscsh.dgt.domain.post.domain.RecruitPeriod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Hashtable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class PostRequest {

    @Size(min = 2, max = 100, message = "제목은 최소 2글자 ~ 100글자 내로 작성돼야 합니다.")
    @ToString.Include
    @NotBlank
    private String title;

    @Size(max = 4000, message = "4000글자 내로 작성돼야 합니다.")
    @ToString.Include
    private String article;

//    @PositiveOrZero
//    @ToString.Include
//    private int viewCnt;

    public Post toEntity(Long memberId) {
        return Post.builder()
                .memberId(memberId)
                .title(title)
                .article(article)
//                .viewCnt(viewCnt)
                .build();
    }

}
