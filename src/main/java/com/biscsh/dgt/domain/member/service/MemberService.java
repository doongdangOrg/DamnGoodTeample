package com.biscsh.dgt.domain.member.service;

import org.springframework.stereotype.Service;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.domain.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void signUp(SignUpRequest signUpRequest){
		Member newMember = signUpRequest.toEntity();

		System.out.println(newMember.getEmail());
		memberRepository.save(newMember);
	}
}
