package com.biscsh.dgt.domain.post;

import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;

import java.time.LocalDateTime;

public class PostData {

//    private PostData() {}
    public static PostResponse postResponse() {
        return PostResponse.builder()
                .postId(1L)
                .build();
    }

    public static PostRequest postRequest() {
        return PostRequest.builder()
                .title("공fea")
                .article("z")
                .recruitCnt(5)
                .activatePeriod(ActivatePeriod.of(LocalDateTime.of(2023, 7, 19, 12, 43, 10), LocalDateTime.of(2023, 8, 1, 0, 0)))
                .recruitPeriod(ActivatePeriod.of(LocalDateTime.of(2023, 7, 19, 12, 43, 10), LocalDateTime.of(2023, 8, 1, 0, 0)))
                .isTest(false)
                .build();
    }
}
