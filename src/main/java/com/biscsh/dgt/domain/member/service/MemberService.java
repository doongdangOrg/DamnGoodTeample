package com.biscsh.dgt.domain.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	//TODO: 에러처리 필요(현재는 return null)
	private final MemberRepository memberRepository;

	public Long signup(SignUpRequest signUpRequest){
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

		return savedMember.getId();
	}

	private Optional<Member> isExistEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	private boolean isExistNickname(String nickname) {
		Optional<Member> result = memberRepository.findByNickname(nickname);

		return result.isPresent();
	}
}
