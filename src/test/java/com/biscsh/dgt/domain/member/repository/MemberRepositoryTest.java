package com.biscsh.dgt.domain.member.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.InfoUpdateRequest;

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

	private InfoUpdateRequest infoUpdateRequest(){
		return new InfoUpdateRequest("updateName", "updateNickname","010-1234-1234");
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
		assertThat(saved).isNotEmpty();
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
		assertThat(saved).isNotEmpty();
		assertThat(saved.get().getNickname()).isEqualTo(member.getNickname());
	}


	@DisplayName("회원 정보 수정 테스트")
	@Test
	void test_update_member(){
	    //given
		Member member = member();
		InfoUpdateRequest infoUpdateRequest = infoUpdateRequest();

	    //when
		Member saved = memberRepository.save(member);

		saved.updateName(infoUpdateRequest.getName());
		saved.updateNickname(infoUpdateRequest.getNickname());
		saved.updatePhoneNumber(infoUpdateRequest.getPhoneNumber());

		memberRepository.save(saved);

		Optional<Member> updated = memberRepository.findByEmail(saved.getEmail());

		//then
		assertThat(updated).isNotEmpty();
		assertThat(updated.get().getName()).isEqualTo(infoUpdateRequest.getName());
		assertThat(updated.get().getNickname()).isEqualTo(infoUpdateRequest.getNickname());
		assertThat(updated.get().getPhoneNumber()).isEqualTo(infoUpdateRequest.getPhoneNumber());
	}
}