package com.biscsh.dgt.domain.post.controller;

import com.biscsh.dgt.domain.post.PostData;
import com.biscsh.dgt.domain.post.domain.ActivatePeriod;
import com.biscsh.dgt.domain.post.dto.PostRequest;
import com.biscsh.dgt.domain.post.dto.PostResponse;
import com.biscsh.dgt.domain.post.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.biscsh.dgt.domain.post.PostData.postRequest;
import static com.biscsh.dgt.domain.post.PostData.postResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    @DisplayName("공고 올리기 성공")
    public void postSuccess() throws Exception {
        // controller -> service
        PostRequest request = postRequest();
        PostResponse response = postResponse();
        lenient().when(postService.createPost(any(PostRequest.class), any(Long.class)))
                .thenReturn(response);
        // client -> controller
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/posts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new GsonBuilder()
                                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                                .registerTypeAdapter(Duration.class, new DurationTypeAdapter())
                                .create().toJson(request)
                        )
        );
        System.out.println(resultActions.andDo(result -> System.out.println(result.getResponse().getContentAsString())));
        // controller -> client
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.post", response.isPost()).exists());
    }


}
