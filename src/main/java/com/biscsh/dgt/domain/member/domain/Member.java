package com.biscsh.dgt.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Email(message = "이메일 형식이 아닙니다.")
	@NotBlank
	private String email;

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

	private Member(Long id, String email, String phoneNumber, String nickname, String name, String password) {
		this.id = id;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nickname = nickname;
		this.name = name;
		this.password = password;
	}

	public void updatePhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public void updateNickname(String nickname){
		this.nickname = nickname;
	}

	public void updateName(String name){
		this.name = name;
	}
}
