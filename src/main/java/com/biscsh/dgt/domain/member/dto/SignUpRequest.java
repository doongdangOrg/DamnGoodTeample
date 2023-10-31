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
		return Member.builder()
			.email(this.getEmail())
			.password(this.getPassword())
			.name(this.getName())
			.phoneNumber(this.getPhoneNumber())
			.nickname(this.getNickname())
			.build();
	}
}
