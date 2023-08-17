package com.biscsh.dgt.domain.member.service;

import static com.biscsh.dgt.domain.member.exception.MemberErrorCode.*;

import org.springframework.stereotype.Service;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.LogInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.exception.MemberException;
import com.biscsh.dgt.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	//TODO: 에러처리 필요(현재는 return null)
	private final MemberRepository memberRepository;

	public boolean signUp(SignUpRequest signUpRequest){
		//중복 이메일 체크
		checkDuplicateEmail(signUpRequest.getEmail());

		//중복 닉네임 체크
		checkDuplicateNickname(signUpRequest.getNickname());

		Member newMember = signUpRequest.toEntity();
		memberRepository.save(newMember);

		return true;
	}

	public Long logIn(LogInRequest logInRequest){
		//이메일을 통한 멤버 조회
		Member member = getMember(logInRequest.getEmail());

		//비밀번호 일치 여부 확인
		checkPassword(logInRequest.getPassword(), member.getPassword());

		return member.getId();
	}

	private void checkDuplicateEmail(String email){
		if(memberRepository.findByEmail(email).isPresent()){
			throw new MemberException(EMAIL_ALREADY_EXIST);
		}
	}

	private Member getMember(String email){
		return memberRepository.findByEmail(email).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
	}

	private void checkDuplicateNickname(String nickname){
		if(memberRepository.findByNickname(nickname).isPresent()){
			throw new MemberException(NICKNAME_ALREADY_EXIST);
		}
	}

	private void checkPassword(String requestPassword, String memberPassword){
		if(!requestPassword.equals(memberPassword) ){
			throw new MemberException(PASSWORD_UN_MATCH);
		}
	}
}
