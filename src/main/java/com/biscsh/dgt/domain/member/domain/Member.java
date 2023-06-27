package com.biscsh.dgt.domain.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {
	@Id
	private Long memberId;

	private String phoneNumber;

	private String nickname;

	private String name;

	private String password;
}
