package com.biscsh.dgt.domain.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.SignInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.exception.MemberErrorCode;
import com.biscsh.dgt.domain.member.exception.MemberException;
import com.biscsh.dgt.domain.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	private final PasswordEncoder encoder = new BCryptPasswordEncoder();


	private SignUpRequest signUpRequest() {
		return SignUpRequest.builder()
			.email("test@test.com")
			.password("1234")
			.name("test")
			.phoneNumber("010-XXXX-XXXX")
			.nickname("test")
			.build();
	}
	private SignInRequest logInRequest(){
		SignInRequest request = SignInRequest.builder()
			.email("test@test.com")
			.password("1234")
			.build();
		return request;
	}

	@DisplayName("회원가입 성공 테스트")
	@Test
	void test_signup_success(){
	    //given
		SignUpRequest request = signUpRequest();
		doReturn(request.toEntity()).when(memberRepository).save(any(Member.class));
	    //when
		Boolean response = memberService.signUp(request);

		//then
		Assertions.assertEquals(response, true);
		assertThat(request.getEmail()).isEqualTo(request.toEntity().getEmail());
	}

	@DisplayName("회원가입 실패 테스트 - 이메일 중복")
	@Test
	void test_signup_fail_by_email(){
	    //given
		SignUpRequest request = signUpRequest();
		doReturn(Optional.of(request.toEntity())).when(memberRepository).findByEmail(request.getEmail());
		//when
		MemberException exception = assertThrows(MemberException.class, () -> memberService.signUp(request));

		//then
		assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.EMAIL_ALREADY_EXIST);
	}

	@DisplayName("회원가입 실패 테스트 - 닉네임 중복")
	@Test
	void test_signup_fail_by_nickname(){
		//given
		SignUpRequest request = signUpRequest();
		doReturn(Optional.of(request.toEntity())).when(memberRepository).findByNickname(request.getNickname());
		//when
		MemberException exception = assertThrows(MemberException.class, () -> memberService.signUp(request));

		//then
		assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.NICKNAME_ALREADY_EXIST);
	}

	@DisplayName("로그인 성공 테스트")
	@Test
	void test_login_success (){
	    //given
		SignInRequest signInRequest = logInRequest();
		Member member = new Member.MemberBuilder()
			.setId(1L)
			.setEmail(signInRequest.getEmail())
			.setPassword(signInRequest.getPassword())
			.build();
		doReturn(Optional.of(member)).when(memberRepository).findByEmail(signInRequest.getEmail());

	    //when
		Long loginMemberId = memberService.signIn(signInRequest);

		//then
		assertThat(loginMemberId).isEqualTo(1L);
	}

	@DisplayName("로그인 실패 테스트 - 존재하지 않는 사용자")
	@Test
	void test_login_fail_by_Member(){
	    //given
	    SignInRequest signInRequest = logInRequest();
		doReturn(Optional.empty()).when(memberRepository).findByEmail(signInRequest.getEmail());

		//when
		MemberException exception = assertThrows(MemberException.class, () -> memberService.signIn(signInRequest));

		//then
		assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.MEMBER_NOT_FOUND);
	}

	@DisplayName("로그인 실패 테스트 - 비밀번호 불일치")
	@Test
	void test_login_fail_by_password(){
	    //given
		SignInRequest signInRequest = logInRequest();
		Member member = new Member.MemberBuilder()
			.setId(1L)
			.setEmail(signInRequest.getEmail())
			.setPassword(encoder.encode(signInRequest.getPassword()+"1"))
			.build();
		//when

	    //then
		assertThat(encoder.matches(signInRequest.getPassword(), member.getPassword())).isFalse();

	}


}