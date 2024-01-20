package com.biscsh.dgt.domain.member.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biscsh.dgt.config.signin.SignInRequired;
import com.biscsh.dgt.domain.member.domain.Member;
import com.biscsh.dgt.domain.member.dto.InfoUpdateRequest;
import com.biscsh.dgt.domain.member.dto.SignInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signUp(@RequestBody SignUpRequest signUpRequest) {
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		Boolean signupSuccess = memberService.signUp(signUpRequest);

		return ResponseEntity.status(CREATED)
				.body(signupSuccess);
	}

	@PostMapping("/signin")
	public ResponseEntity<Boolean> signIn(HttpSession session, @RequestBody SignInRequest signInRequest) {
		Long signInMemberId = memberService.signIn(signInRequest);

		session.setAttribute("signIn", signInMemberId);
		session.setMaxInactiveInterval(1800);

		return ResponseEntity.status(ACCEPTED).body(true);
	}

	@SignInRequired
	@PostMapping("/signout")
	public ResponseEntity<Object> signOut(HttpSession session) {

		session.invalidate();

		return ResponseEntity.status(OK).build();
	}

	@SignInRequired
	@PatchMapping("/info")
	public ResponseEntity<Boolean> update(HttpSession session, @RequestBody InfoUpdateRequest infoUpdateRequest) {
		Long signInMemberId = (Long)session.getAttribute("signIn");

		memberService.updateInfo(signInMemberId, infoUpdateRequest);

		return ResponseEntity.status(CREATED).body(true);
	}


	@SignInRequired
	@GetMapping("/info")
	public ResponseEntity<Member> info(HttpSession session) {
		Long signInMemberId = (Long)session.getAttribute("signIn");
		Member member = memberService.getMember(signInMemberId);

		return ResponseEntity.status(OK).body(member);
	}

	@SignInRequired
	@DeleteMapping
	public ResponseEntity<Void> delete(HttpSession session) {
		Long signInMemberId = (Long)session.getAttribute("signIn");

		session.invalidate();
		memberService.delete(signInMemberId);

		return ResponseEntity.status(OK).build();
	}
}
