package com.biscsh.dgt.domain.member.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public SignUpResponse signup(SignUpRequest signUpRequest){
		//중복 이메일 체크
		if(isExistEmail(signUpRequest.getEmail()).isPresent()){
			return null;
		}

		//중복 닉네임 체크
		if(isExistNickname(signUpRequest.getNickname())){
			return null;
		}

		signUpRequest.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
		Member newMember = signUpRequest.toEntity();
		Member savedMember = memberRepository.save(newMember);

		return SignUpResponse.builder()
			.email(savedMember.getEmail())
			.name(savedMember.getName())
			.password(savedMember.getPassword())
			.nickname(savedMember.getNickname())
			.phoneNumber(savedMember.getPhoneNumber())
			.build();
	}

	public Long login(LogInRequest logInRequest){
		Optional<Member> member = memberRepository.findByEmail(logInRequest.getEmail());
		if(member.isEmpty()){
			return null;
		}

		if(!member.get().getPassword().equals(logInRequest.getPassword())){
			return null;
		}
		return member.get().getId();
	}

	public Optional<Member> isExistEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	public boolean isExistNickname(String nickname) {
		Optional<Member> result = memberRepository.findByNickname(nickname);

		return result.isPresent();
	}
}
