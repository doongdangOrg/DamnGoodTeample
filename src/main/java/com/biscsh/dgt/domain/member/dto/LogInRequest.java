package com.biscsh.dgt.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogInRequest {
	private String email;
	private String password;
}
