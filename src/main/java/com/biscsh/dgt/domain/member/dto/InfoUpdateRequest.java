package com.biscsh.dgt.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InfoUpdateRequest {
	private String nickname;
	private String name;
	private String phoneNumber;

	public InfoUpdateRequest(String nickname, String name, String phoneNumber) {
		this.nickname = nickname;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
}