package com.biscsh.dgt.domain.member.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

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

	@DisplayName("회원 저장 성공 테스트")
	@Test
	void test_signup_success(){
	    //given
		Member member = member();

		//when
		Member saved = memberRepository.save(member());

	    //then
		assertThat(saved.getEmail()).isEqualTo(member.getEmail());
	}
	@DisplayName("회원 저장 실패 테스트 - 이메일 중복")
	@Test
	void test_signup_fail_by_email(){
	    //given
		memberRepository.save(member());

	    //when
		Member member = member();
		Optional<Member> find = memberRepository.findByEmail(member.getEmail());

		//then
		assertThat(find.get().getEmail()).isEqualTo(member.getEmail());
	}

	@DisplayName("회원 저장 실패 테스트 - 닉네임 중복")
	@Test
	void test_signup_fail_by_nickname(){
		//given
		memberRepository.save(member());

		//when
		Member member = member();
		Optional<Member> find = memberRepository.findByNickname(member.getNickname());

		//then
		assertThat(find.get().getNickname()).isEqualTo(member.getNickname());
	}
}