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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

	public static Member newMember(String email, String phoneNumber, String nickname, String name, String password){
		return new Member.MemberBuilder()
			.setEmail(email)
			.setPhoneNumber(phoneNumber)
			.setNickname(nickname)
			.setName(name)
			.setPassword(password)
			.build();
	}

	public static class MemberBuilder {
		private Long id;
		private String email;
		private String phoneNumber;
		private String nickname;
		private String name;
		private String password;

		public MemberBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public MemberBuilder setEmail(String email){
			this.email = email;
			return this;
		}

		public MemberBuilder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public MemberBuilder setNickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public MemberBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public MemberBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Member build() {
			return new Member(id, email, phoneNumber, nickname, name, password);
		}
	}

}
