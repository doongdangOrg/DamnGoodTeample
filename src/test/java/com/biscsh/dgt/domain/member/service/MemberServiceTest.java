package com.biscsh.dgt.domain.member.service;

import static org.assertj.core.api.Assertions.*;
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

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.dto.SignUpResponse;
import com.biscsh.dgt.domain.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	private SignUpRequest signUpRequest() {
		return SignUpRequest.builder()
			.email("test@test.com")
			.password("1234")
			.name("test")
			.phoneNumber("010-XXXX-XXXX")
			.nickname("test")
			.build();
	}

	@DisplayName("회원가입 성공 테스트")
	@Test
	void test_signup_success(){
	    //given
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		SignUpRequest request = signUpRequest();
		String encodeRequestPwd = bCryptPasswordEncoder.encode(request.getPassword());
		doReturn(new Member.MemberBuilder()
			.setEmail(request.getEmail())
			.setPassword(encodeRequestPwd)
			.setName(request.getName())
			.setNickname(request.getNickname())
			.setPhoneNumber(request.getPhoneNumber())
			.build()
		).when(memberRepository).save(any(Member.class));
	    //when
		SignUpResponse response = memberService.signup(request);

		//then
		Assertions.assertEquals(request.getEmail(), response.getEmail());
		assertThat(bCryptPasswordEncoder.matches(request.getPassword(), response.getPassword())).isTrue();
	}

	@DisplayName("회원가입 실패 테스트 - 이메일 중복")
	@Test
	void test_signup_fail_by_email(){
	    //given
		SignUpRequest request = signUpRequest();
		doReturn(Optional.of(request.toEntity())).when(memberRepository).findByEmail(request.getEmail());
		//when
		SignUpResponse response = memberService.signup(request);

		//then
		assertThat(response).isEqualTo(null);
	}

	@DisplayName("회원가입 실패 테스트 - 닉네임 중복")
	@Test
	void test_signup_fail_by_nickname(){
		//given
		SignUpRequest request = signUpRequest();
		doReturn(Optional.of(request.toEntity())).when(memberRepository).findByNickname(request.getNickname());
		//when
		SignUpResponse response = memberService.signup(request);

		//then
		assertThat(response).isEqualTo(null);
	}
}