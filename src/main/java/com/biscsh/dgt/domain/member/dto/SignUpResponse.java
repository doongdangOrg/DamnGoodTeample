package com.biscsh.dgt.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {
	private String email;
	private String password;
	private String nickname;
	private String phoneNumber;
	private String name;

}
