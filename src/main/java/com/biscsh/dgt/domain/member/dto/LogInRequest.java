package com.biscsh.dgt.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest {
	private String email;
	private String password;
}
