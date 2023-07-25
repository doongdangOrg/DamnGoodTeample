package com.biscsh.dgt.domain.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.LogInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.dto.SignUpResponse;
import com.biscsh.dgt.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	//TODO: 에러처리 필요(현재는 return null)
	private final MemberRepository memberRepository;

	public SignUpResponse signUp(SignUpRequest signUpRequest){
		//중복 이메일 체크
		if(isExistEmail(signUpRequest.getEmail()).isPresent()){
			return null;
		}

		//중복 닉네임 체크
		if(isExistNickname(signUpRequest.getNickname())){
			return null;
		}

		Member newMember = signUpRequest.toEntity();
		Member savedMember = memberRepository.save(newMember);

		return SignUpResponse.builder()
			.email(savedMember.getEmail())
			.password(savedMember.getPassword())
			.phoneNumber(savedMember.getPhoneNumber())
			.nickname(savedMember.getNickname())
			.name(savedMember.getName())
			.build();
	}

	public Long logIn(LogInRequest logInRequest) {
		Optional<Member> existEmail = isExistEmail(logInRequest.getEmail());
		if(existEmail.isEmpty()){
			return null;
		}

		Member searchedMember = existEmail.get();

		if(searchedMember.getPassword().equals(logInRequest.getPassword())){
			return null;
		}

		return searchedMember.getId();
	}
	//굳이 이메일과 닉네임 중복을 분리한 이유는 추후 프론트에서 따로 호출 가능하게끔하려고 한다.
	private Optional<Member> isExistEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	private boolean isExistNickname(String nickname) {
		Optional<Member> result = memberRepository.findByNickname(nickname);

		return result.isPresent();
	}
}
