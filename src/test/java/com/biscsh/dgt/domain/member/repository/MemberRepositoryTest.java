package com.biscsh.dgt.domain.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biscsh.dgt.domain.member.domain.Member;

@DataJpaTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	private Member member(){
		return new Member.MemberBuilder()
			.setName("test")
			.setNickname("test")
			.setEmail("test@test.com")
			.setPassword("1234")
			.setPhoneNumber("010-1234-2345")
			.build();
	}

	@DisplayName("회원 저장 테스트")
	@Test
	void test_signup_success(){
	    //given
		Member member = member();

		//when
		Member saved = memberRepository.save(member());

	    //then
		Assertions.assertThat(saved.getEmail()).isEqualTo(member.getEmail());
	}
}