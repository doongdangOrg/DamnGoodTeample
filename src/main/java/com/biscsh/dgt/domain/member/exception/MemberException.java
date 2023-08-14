package com.biscsh.dgt.domain.member.exception;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException{
	private final MemberErrorCode errorCode;
	private final String errorMessage;

	public MemberException(MemberErrorCode errorCode){
		this.errorCode = errorCode;
		this.errorMessage = errorCode.getDescription();
	}
}
