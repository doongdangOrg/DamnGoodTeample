package com.biscsh.dgt.domain.member.controller;

import static org.assertj.core.api.Assertions.*;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.biscsh.dgt.domain.member.dto.LogInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.dto.SignUpResponse;
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

	private SignUpResponse signUpResponse() {
		return SignUpResponse.builder()
			.email("test@test.com")
			.password("1234")
			.name("test")
			.phoneNumber("010-XXXX-XXXX")
			.nickname("test")
			.build();
	}

	private LogInRequest logInRequest(){
		LogInRequest request = new LogInRequest();
		request.setEmail("test@test.com");
		request.setPassword("1234");
		return request;
	}

	@DisplayName("회원 가입 성공 테스트")
	@Test
	void test_signup_success() throws Exception {
		//given
		SignUpRequest signUpRequest = signUpRequest();
		SignUpResponse signUpResponse = signUpResponse();
		doReturn(signUpResponse).when(memberService)
			.signup(any(SignUpRequest.class));
		//when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(signUpRequest)));
		//then

		ResultActions resultActions1 = resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("email", signUpResponse.getEmail()).exists())
			.andExpect(jsonPath("password", signUpResponse.getPassword()).exists())
			.andExpect(jsonPath("name", signUpResponse.getName()).exists())
			.andExpect(jsonPath("nickname", signUpResponse.getNickname()).exists())
			.andExpect(jsonPath("phoneNumber", signUpResponse.getPhoneNumber()).exists());
	}

	@DisplayName("회원가입 실패 테스트")
	@Test
	void test_signup_fail() throws Exception {
	    //given
		SignUpRequest signUpRequest = signUpRequest();
		doReturn(null).when(memberService).signup(any(SignUpRequest.class));

	    //when
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(signUpRequest))
		);

	    //then
		ResultActions resultActions = result.andExpect(status().isBadRequest());
	}

	@DisplayName("로그인 성공 테스트")
	@Test
	void test_login_success() throws Exception {
	    //given
		LogInRequest request = logInRequest();
		doReturn(1L).when(memberService).login(any(LogInRequest.class));

		//when
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(request))
		);

		//then
		MvcResult mvcResult = result.andExpect(status().isAccepted()).andReturn();
		Long resultId = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Long.class);
		assertThat(resultId).isEqualTo(1L);
	}

	@DisplayName("로그인 실패 테스트")
	@Test
	void test_login_fail() throws Exception{
	    //given
		LogInRequest request = logInRequest();
		doReturn(null).when(memberService).login(any(LogInRequest.class));

		//when
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(request))
		);

		//then
		MvcResult mvcResult = result.andExpect(status().isBadRequest()).andReturn();
		Long resultId = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Long.class);
		assertThat(resultId).isEqualTo(null);
	}
}