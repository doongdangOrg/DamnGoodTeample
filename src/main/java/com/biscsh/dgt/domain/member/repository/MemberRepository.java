package com.biscsh.dgt.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biscsh.dgt.domain.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);
	Optional<Member> findByNickname(String nickname);
}
