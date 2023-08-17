package com.biscsh.dgt.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
	MEMBER_NOT_FOUND("존재하지 않는 사용자입니다."),
	EMAIL_ALREADY_EXIST("이미 존재하는 이메일 계정입니다."),
	NICKNAME_ALREADY_EXIST("이미 존재하는 닉네임입니다."),
	PASSWORD_UN_MATCH("비밀번호가 일치하지 않습니다.");


	private final String description;
}
