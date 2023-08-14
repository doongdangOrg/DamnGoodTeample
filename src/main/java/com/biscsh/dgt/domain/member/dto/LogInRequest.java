package com.biscsh.dgt.domain.member.dto;

import lombok.Getter;

@Getter
public class LogInRequest {
	private String email;
	private String password;
}
