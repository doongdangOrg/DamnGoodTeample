package com.biscsh.dgt.domain.member.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.InfoUpdateRequest;
import com.biscsh.dgt.domain.member.dto.SignInRequest;
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

	private MockHttpSession mockitoSession;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
		mockitoSession = new MockHttpSession();
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

	private SignInRequest signInRequest() {
		return SignInRequest.builder()
				.email("test@test.com")
				.password("1234")
				.build();
	}

	private Member member() {
		return Member.builder()
				.email("test@test.com")
				.password("1234")
				.nickname("test")
				.name("test")
				.phoneNumber("010-1234-5678")
				.build();
	}

	private InfoUpdateRequest infoUpdateRequest() {
		return new InfoUpdateRequest("updateName", "updateNickname", "010-1234-1234");
	}

	@DisplayName("회원 가입 성공 테스트")
	@Test
	void test_signup_success() throws Exception {
		//given
		SignUpRequest signUpRequest = signUpRequest();
		doReturn(true).when(memberService)
				.signUp(any(SignUpRequest.class));
		//when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/members/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(signUpRequest)));
		//then
		resultActions.andExpect(status().isCreated());
	}

	@DisplayName("로그인 성공 테스트")
	@Test
	void test_sign_in_success() throws Exception {
		//given
		SignInRequest request = signInRequest();
		doReturn(1L).when(memberService).signIn(any(SignInRequest.class));

		//when
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/members/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(request))
		);

		//then
		result.andExpect(status().isAccepted());
	}

	@DisplayName("회원 정보 수정 실패 테스트 - 미 로그인 상태")
	@Test
	void test_infoUpdate_fail_not_sign_in() throws Exception {
		//given
		InfoUpdateRequest request = infoUpdateRequest();
		//when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.patch("/members/info")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(request))
		);

		// then
		resultActions.andExpect(status().isUnauthorized());
	}

	@DisplayName("회원 정보 수정 성공 테스트")
	@Test
	void test_infoUpdate_success() throws Exception {
		//given
		InfoUpdateRequest request = infoUpdateRequest();

		mockitoSession.setAttribute("signIn", 1L);

		//when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.patch("/members/info")
				.session(mockitoSession)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(request))
		);

		//then
		resultActions.andExpect(status().isCreated());
	}

	@DisplayName("회원 정보 조회 성공 테스트")
	@Test
	void test_get_info_success() throws Exception {
		//given
		mockitoSession.setAttribute("signIn", 1L);

		Member member = member();

		doReturn(member).when(memberService).getMember(anyLong());

		//when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/members/info")
				.session(mockitoSession)
				.contentType(MediaType.APPLICATION_JSON)

		);

		//then
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andDo(print());
	}

	@DisplayName("로그아웃 성공 테스트")
	@Test
	void test_sign_out_success() throws Exception {
		//given
		mockitoSession.setAttribute("signIn", 1L);

		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/members/signout").session(mockitoSession));

		//then
		resultActions.andExpect(status().isOk());
		assertThat(mockitoSession.isInvalid()).isEqualTo(true);
	}

	@DisplayName("로그아웃 실패 테스트 - 미 로그인 상태에서 시도")
	@Test
	void test_sign_out_fail() throws Exception {
		//given

		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/members/signout").session(mockitoSession));

		//then
		resultActions.andExpect(status().isUnauthorized());
	}
}