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
import com.biscsh.dgt.domain.member.dto.InfoUpdateRequest;
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
			.phoneNumber("010-1234-5678")
			.nickname("test")
			.build();
	}
	private SignInRequest signInRequest(){
		return SignInRequest.builder()
			.email("test@test.com")
			.password("1234")
			.build();
	}
	private InfoUpdateRequest infoUpdateRequest(){
		return new InfoUpdateRequest("updateName", "updateNickname","010-1234-1234");
	}

	private Member member(){
		return Member.builder()
			.email("test@test.com")
			.password("1234")
			.nickname("test")
			.name("test")
			.phoneNumber("010-1234-5678")
			.build();
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
	void test_sign_in_success (){
	    //given
		SignInRequest signInRequest = signInRequest();
		Member member = member();
		doReturn(Optional.of(member)).when(memberRepository).findByEmail(signInRequest.getEmail());

	    //when
		Long signInId = memberService.signIn(signInRequest);

		//then
		assertThat(signInId).isEqualTo(null);
	}

	@DisplayName("로그인 실패 테스트 - 존재하지 않는 사용자")
	@Test
	void test_sign_in_fail_by_Member(){
	    //given
	    SignInRequest signInRequest = signInRequest();
		doReturn(Optional.empty()).when(memberRepository).findByEmail(signInRequest.getEmail());

		//when
		MemberException exception = assertThrows(MemberException.class, () -> memberService.signIn(signInRequest));

		//then
		assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.MEMBER_NOT_FOUND);
	}

	@DisplayName("로그인 실패 테스트 - 비밀번호 불일치")
	@Test
	void test_sign_in_fail_by_password(){
	    //given
		SignInRequest signInRequest = signInRequest();
		Member member = Member.builder()
			.email(signInRequest.getEmail())
			.password(encoder.encode(signInRequest.getPassword()+"1"))
			.build();
		//when

	    //then
		assertThat(encoder.matches(signInRequest.getPassword(), member.getPassword())).isFalse();
	}

	@DisplayName("회원정보 수정 실패 테스트 - 멤버가 존재하지 않는 경우")
	@Test
	//what is ParameterizedTest???
	void test_info_update_fail_by_no_member(){
	    //given
		Long memberId = 1L;
		InfoUpdateRequest request = infoUpdateRequest();

		doThrow(new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)).when(memberRepository).findById(anyLong());


	    //when
		MemberException exception = assertThrows(MemberException.class, () -> memberService.updateInfo(memberId, request));

	    //then
		assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.MEMBER_NOT_FOUND);

	}

	@DisplayName("회원정보 수정 성공 테스트")
	@Test
	void test_info_update_success(){
		//given
		Long memberId = 1L;
		InfoUpdateRequest request = infoUpdateRequest();
		Member member = member();

		doReturn(Optional.of(member)).when(memberRepository).findById(anyLong());

		//when
		memberService.updateInfo(memberId, request);
		Optional<Member> updateMember = memberRepository.findById(memberId);

		//then
		verify(memberRepository, times(2)).findById(anyLong());
		verify(memberRepository, times(1)).save(any(Member.class));
		assertThat(updateMember.get().getNickname()).isEqualTo(request.getNickname());

	}
}