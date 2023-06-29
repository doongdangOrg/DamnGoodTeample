package com.biscsh.dgt.domain.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Size(min = 13, max = 13)
	@NotNull
	private String phoneNumber;

	@Size(min = 2, max = 20, message = "2~20자 내로 작성돼야 합니다.")
	@NotNull
	private String nickname;

	@Size(min = 2, max = 20, message = "2~20자 내로 작성돼야 합니다.")
	@NotNull
	private String name;

	@Size(min = 2, max = 20, message = "2~20자 내로 작성돼야 합니다.")
	@NotNull
	private String password;
}
