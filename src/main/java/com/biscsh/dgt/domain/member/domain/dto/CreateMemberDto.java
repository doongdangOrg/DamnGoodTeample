package com.biscsh.dgt.domain.member.domain.dto;

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
public class CreateMemberDto {
	private Long id;
	private String password;
	private String nickname;
	private String phoneNumber;
	private String name;
}
