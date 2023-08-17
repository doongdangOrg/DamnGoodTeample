package com.biscsh.dgt.domain.member.dto;

import com.biscsh.dgt.domain.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
	private String email;
	private String password;
	private String nickname;
	private String phoneNumber;
	private String name;

	public void setPassword(String password) {
		this.password = password;
	}

	public Member toEntity(){
		return new Member.MemberBuilder()
			.setEmail(this.getEmail())
			.setPassword(this.getPassword())
			.setName(this.getName())
			.setPhoneNumber(this.getPhoneNumber())
			.setNickname(this.getNickname())
			.build();
	}
}
