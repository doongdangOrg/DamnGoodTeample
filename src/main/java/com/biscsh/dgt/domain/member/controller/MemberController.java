package com.biscsh.dgt.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biscsh.dgt.domain.member.dto.SignInRequest;
import com.biscsh.dgt.domain.member.dto.SignUpRequest;
import com.biscsh.dgt.domain.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signUp(@RequestBody SignUpRequest signUpRequest){
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		Boolean signupSuccess = memberService.signUp(signUpRequest);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(signupSuccess);
	}

	@PostMapping("/signin")
	public ResponseEntity<Boolean> signIn(HttpServletRequest servletRequest, @RequestBody SignInRequest signInRequest){
		Long signInMemberId = memberService.signIn(signInRequest);

		HttpSession session = servletRequest.getSession();
		session.setAttribute("signIn", signInMemberId);
		session.setMaxInactiveInterval(1800);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
	}
}
