package com.biscsh.dgt.domain.member.domain;

import com.biscsh.dgt.domain.member.domain.dto.CreateMemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	private Member(Long id, String phoneNumber, String nickname, String name, String password) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.nickname = nickname;
		this.name = name;
		this.password = password;
	}

	public static class MemberBuilder {
		private Long id;
		private String phoneNumber;
		private String nickname;
		private String name;
		private String password;

		public MemberBuilder setId(Long id) {
			this.id = id;
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
			return new Member(id, phoneNumber, nickname, name, password);
		}
	}

	public static Member toEntity(CreateMemberDto createMemberDto){
		return new Member.MemberBuilder()
			.setId(createMemberDto.getId())
			.setPassword(createMemberDto.getPassword())
			.setName(createMemberDto.getName())
			.setNickname(createMemberDto.getNickname())
			.setPhoneNumber(createMemberDto.getPhoneNumber())
			.build();
	}

}
