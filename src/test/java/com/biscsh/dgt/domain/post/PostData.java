package com.biscsh.dgt.domain.post;

import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;

import java.time.LocalDateTime;

public class PostData {

//    private PostData() {}
    public static PostResponse postResponse() {
        return PostResponse.builder()
                .isPost(true)
                .build();
    }

    public static PostRequest postRequest() {
        return PostRequest.builder()
                .title("공")
                .isTest(false)
                .activatePeriod(ActivatePeriod.of(LocalDateTime.now(), LocalDateTime.of(2023, 8, 1, 0, 0)))
                .article("z")
                .recruitPeriod(ActivatePeriod.of(LocalDateTime.now(), LocalDateTime.of(2023, 8, 1, 0, 0)))
                .build();
    }
}
