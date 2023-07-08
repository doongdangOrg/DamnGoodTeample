package com.biscsh.dgt.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.domain.dto.CreateMemberDto;
import com.biscsh.dgt.domain.member.repository.MemberRepository;

@SpringBootTest
public class MemberTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("DTO를 통한 회원 생성 테스트")
	void createMemberFromDtoTest(){
	    //given
		CreateMemberDto dto = new CreateMemberDto();
		dto.setId(1L);
		dto.setName("whtjrgus");
		dto.setNickname("jorang");
		dto.setPassword("1234");
		dto.setPhoneNumber("010-3033-0404");

	    //when
		Member createdMember = Member.toEntity(dto);

	    //then
		Assertions.assertThat(createdMember.getId()).isEqualTo(dto.getId());
	}

	@Test
	@DisplayName("DTO를 통한 회원 생성과 DB 저장 테스트")
	@Transactional
	void createMemberFromDtoAndSaveToDb(){
	    //given
		CreateMemberDto dto = new CreateMemberDto();
		dto.setId(1L);
		dto.setName("whtjrgus");
		dto.setNickname("jorang");
		dto.setPassword("1234");
		dto.setPhoneNumber("010-3033-0404");

		Member createdMember = Member.toEntity(dto);

	    //when
		Member savedMember = memberRepository.save(createdMember);

		//then
		Assertions.assertThat(savedMember.getId()).isEqualTo(createdMember.getId());
	}
}
