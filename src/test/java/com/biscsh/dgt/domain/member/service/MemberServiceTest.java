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
		SignUpRequest request = signUpRequest();
		doReturn(request.toEntity()).when(memberRepository).save(any(Member.class));
	    //when
		SignUpResponse response = memberService.signup(request);

		//then
		Assertions.assertEquals(request.getEmail(), response.getEmail());
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
}