package com.biscsh.dgt.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InfoUpdateRequest {
	String nickname;
	String name;
	String phoneNumber;
}
