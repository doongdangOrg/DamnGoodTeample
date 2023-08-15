package com.biscsh.dgt.domain.member.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.biscsh.dgt.domain.member.dto.LogInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.service.MemberService;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {
	private MockMvc mockMvc;
	@Mock
	private MemberService memberService;
	@InjectMocks
	private MemberController memberController;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}

	private SignUpRequest signUpRequest() {
		return SignUpRequest.builder()
			.email("test@test.com")
			.password("1234")
			.name("test")
			.phoneNumber("010-XXXX-XXXX")
			.nickname("test")
			.build();
	}

	private LogInRequest logInRequest(){
		LogInRequest request = LogInRequest.builder()
			.email("test@test.com")
			.password("1234")
			.build();
		return request;
	}

	@DisplayName("회원 가입 성공 테스트")
	@Test
	void test_signup_success() throws Exception {
		//given
		SignUpRequest signUpRequest = signUpRequest();
		doReturn(true).when(memberService)
			.signup(any(SignUpRequest.class));
		//when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(signUpRequest)));
		//then
		resultActions.andExpect(status().isCreated());
	}


	@DisplayName("로그인 성공 테스트")
	@Test
	void test_login_success() throws Exception {
	    //given
		LogInRequest request = logInRequest();
		doReturn(true).when(memberService).login(any(LogInRequest.class));

		//when
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(request))
		);

		//then
		result.andExpect(status().isAccepted());
	}

}