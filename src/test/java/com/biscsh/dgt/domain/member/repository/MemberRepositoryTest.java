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
		return Member.builder()
			.name("test")
			.nickname("test")
			.email("test@test.com")
			.password("1234")
			.phoneNumber("010-1234-5678")
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
		assertThat(saved.getEmail()).isEqualTo(member.getEmail());
	}

	@DisplayName("회원 이메일 조회 테스트")
	@Test
	void test_find_by_email(){
	    //given
		Member member = member();

	    //when
		memberRepository.save(member);
		Optional<Member> saved = memberRepository.findByEmail(member.getEmail());

		//then
		assertThat(saved.get().getEmail()).isEqualTo(member.getEmail());
	}

	@DisplayName("회원 닉네임 조회 테스트")
	@Test
	void test_find_by_nickname(){
		//given
		Member member = member();

		//when
		memberRepository.save(member);
		Optional<Member> saved = memberRepository.findByNickname(member.getNickname());

		//then
		assertThat(saved.get().getNickname()).isEqualTo(member.getNickname());
	}

	@DisplayName("회원 아이디 조회 테스트")
	@Test
	void test_find_by_id(){
		Member member = member();

		//when
		Member saved = memberRepository.save(member);

		Optional<Member> memberById = memberRepository.findById(saved.getId());

		//then
		assertThat(memberById.get().getId()).isEqualTo(saved.getId());
	}
}