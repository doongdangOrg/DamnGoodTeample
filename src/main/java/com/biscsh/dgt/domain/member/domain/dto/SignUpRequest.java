package com.biscsh.dgt.domain.member.domain.dto;

import com.biscsh.dgt.domain.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
	private String email;
	private String password;
	private String nickname;
	private String phoneNumber;
	private String name;

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
